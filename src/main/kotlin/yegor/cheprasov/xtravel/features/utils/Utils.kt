package yegor.cheprasov.xtravel.features.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import yegor.cheprasov.xtravel.utils.FileService
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


class Utils(private val fileService: FileService) {
    /**
     * Проверяет папку `file` в корне проекта, и если размер файла превышает порог,
     * сжимает изображение.
     *
     * @param folderName Имя папки в корне проекта.
     * @param maxFileSize Максимальный размер файла в байтах, после которого нужно сжимать.
     */
    suspend fun compressLargeImages(folderName: String, maxFileSize: Long) {
        println("Compress large images: $folderName, $maxFileSize")
        val projectDir = File(System.getProperty("user.dir")) // Корень проекта
        val folder = File(projectDir, folderName)

        if (!folder.exists() || !folder.isDirectory) {
            throw IllegalArgumentException("Папка '$folderName' не существует в корне проекта.")
        }
        println("List: ${folder.listFiles().map { it.name }}")
        // Перебираем все файлы с расширениями изображений
        folder.listFiles()?.forEach { file ->
            if (file.isDirectory) {
                println("FolderName: $folderName")
                println("FileName: ${file.name}")
                println("FilePath: ${file.path}")
                compressLargeImages(folderName + "/${file.name}", maxFileSize)
            } else if (file.isFile && file.extension.lowercase() in listOf("jpg", "jpeg", "png")) {
                if (file.length() > maxFileSize) {
                    println("Сжимаем файл: ${file.name} (Размер: ${file.length()} байт)")
                    compressImage(file)
                } else {
                    println("Файл ${file.name} не требует сжатия (Размер: ${file.length()} байт)")
                }
            }
            println("File: ${file.name}")
        }
    }

    /**
     * Сжимает изображение и сохраняет его с заменой оригинального файла.
     *
     * @param file Файл изображения для сжатия.
     */
    suspend fun compressImage(file: File) = withContext(Dispatchers.IO) {
        val image: BufferedImage = ImageIO.read(file) ?: throw IllegalArgumentException("Не удалось прочитать изображение: ${file.name}")

        // Создаем временный файл для сжатого изображения
        val tempFile = File(file.parent, "compressed_${file.name}")

        // Сохраняем изображение с качеством JPEG
        val writer = ImageIO.getImageWritersByFormatName("jpeg").next()
        val param = writer.defaultWriteParam
        param.compressionMode = javax.imageio.ImageWriteParam.MODE_EXPLICIT
        param.compressionQuality = 0.7f // Качество: 70%

        writer.output = ImageIO.createImageOutputStream(tempFile)
        writer.write(null, javax.imageio.IIOImage(image, null, null), param)
        writer.dispose()

        // Заменяем оригинальный файл сжатым
        if (file.delete()) {
            tempFile.renameTo(file)
            println("Файл ${file.name} успешно сжат.")
        } else {
            println("Не удалось заменить оригинальный файл: ${file.name}")
        }
    }
}