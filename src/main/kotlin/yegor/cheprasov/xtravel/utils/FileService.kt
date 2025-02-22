package yegor.cheprasov.xtravel.utils

import java.io.File

class FileService(private val baseDirectory: String) {

    init {
        val baseDir = File(baseDirectory)
        if (!baseDir.exists()) {
            baseDir.mkdirs()
        }
    }

    fun listAllFilesForCity(countryFolderName: String, cityFolderName: String): List<String> =
        listAllFiles("countries/$countryFolderName/cities/$cityFolderName")

    /**
     * Получить список всех файлов в указанной директории (включая вложенные).
     */
    fun listAllFiles(path: String): List<String> {
        val directory = File(baseDirectory, path)
        // Проверяем, существует ли директория
        if (!directory.exists()) {
            // Создаем директорию, если её нет
            if (!directory.mkdirs()) {
                throw IllegalStateException("Failed to create directory at $path")
            }
        }

        // Проверяем, является ли путь директорией
        if (!directory.isDirectory) {
            throw IllegalArgumentException("Path $path is not a directory")
        }
        return directory.walkTopDown()
            .filter { it.isFile }
            .map { it.relativeTo(File(baseDirectory)).path.replace("\\", "/") }
            .toList()
    }

    /**
     * Получить список всех папок в указанной директории.
     */
    fun listFolders(path: String): List<String> {
        val directory = File(baseDirectory, path)
        if (!directory.exists() || !directory.isDirectory) {
            throw IllegalArgumentException("Path $path does not exist or is not a directory")
        }
        return directory.walkTopDown()
            .filter { it.isDirectory }
            .map { it.relativeTo(File(baseDirectory)).path.replace("\\", "/") } // Пути относительно baseDirectory
            .map { "$baseDirectory/$it" } //
            .toList()
    }

    /**
     * Удалить файл или папку по пути.
     */
    fun delete(path: String): Boolean {
        val target = File(baseDirectory, path)
        if (!target.exists()) {
            throw IllegalArgumentException("Path $path does not exist")
        }
        return target.deleteRecursively()
    }

    /**
     * Создать новую папку по указанному пути.
     */
    fun createFolder(path: String): Boolean {
        val folder = File(baseDirectory, path)
        return folder.mkdirs()
    }

    /**
     * Проверить существование файла или папки по указанному пути.
     */
    fun exists(path: String): Boolean {
        return File(baseDirectory, path).exists()
    }

    /**
     * Записать данные в файл.
     */
    fun writeFile(path: String, content: ByteArray): Boolean {
        val file = File(baseDirectory, path)
        file.parentFile?.mkdirs()
        file.writeBytes(content)
        return file.exists()
    }

    /**
     * Прочитать данные из файла.
     */
    fun readFile(path: String): ByteArray? {
        val file = File(baseDirectory, path)
        if (!file.exists() || !file.isFile) {
            throw IllegalArgumentException("Path $path does not exist or is not a file")
        }
        return file.readBytes()
    }
}