package yegor.cheprasov.xtravel.data.repositories.country

import yegor.cheprasov.xtravel.data.database.entities.country.CountryDTO
import yegor.cheprasov.xtravel.data.database.entities.country.ShortCountryDTO

class CountryRepositoryMock : CountryRepository {
    override suspend fun insert(countryDTO: CountryDTO) = Unit

    override suspend fun fetchByCountryId(countryId: Long): CountryDTO =
        CountriesMockFactory.getCountry(CountriesMockFactory.CountryMock.Japan)

    override suspend fun fetchAllCountries(): List<CountryDTO> =
        listOf(
            CountriesMockFactory.getCountry(CountriesMockFactory.CountryMock.Japan),
            CountriesMockFactory.getCountry(CountriesMockFactory.CountryMock.USA)
        )

    override suspend fun fetchTrendingCountry(): List<ShortCountryDTO> =
        listOf(
            CountriesMockFactory.getShortCountry(CountriesMockFactory.CountryMock.Japan),
            CountriesMockFactory.getShortCountry(CountriesMockFactory.CountryMock.USA)
        )
}

private object CountriesMockFactory {

    fun getCountry(countryMock: CountryMock): CountryDTO =
        CountryDTO(
            countryId = countryMock.countryId,
            countryNameEn = countryMock.countryNameEn,
            countryNameRu = countryMock.countryNameRu,
            countryDescriptionEn = countryMock.countryDescriptionEn,
            countryDescriptionRu = countryMock.countryDescriptionRu,
            flagUrl = countryMock.flagUrl,
            capitalId = countryMock.capitalId,
            population = countryMock.population,
            mainFolderName = countryMock.mainFolderName
        )

    fun getShortCountry(countryMock: CountryMock): ShortCountryDTO =
        ShortCountryDTO(
            countryId = countryMock.countryId,
            countryNameEn = countryMock.countryNameEn,
            countryNameRu = countryMock.countryNameRu,
            flagUrl = countryMock.flagUrl,
            capitalId = countryMock.capitalId,
            capitalNameRu = "Мок",
            capitalNameEn = "Mock",
            mainFolderName = countryMock.mainFolderName
        )

    enum class CountryMock(
        val countryId: Int,
        val countryNameEn: String,
        val countryNameRu: String,
        val countryDescriptionEn: String,
        val countryDescriptionRu: String,
        val flagUrl: String,
        val capitalId: String,
        val population: Int,
        val mainFolderName: String
    ) {
        Japan(
            countryId = 0,
            countryNameEn = "Japan",
            countryNameRu = "Япония",
            countryDescriptionEn = "Japan is an amazing country in East Asia, where modernity is harmoniously combined with ancient traditions. High-tech cities such as Tokyo and Osaka, with their skyscrapers, neon signs and cutting-edge innovations, await travelers here. At the same time, Japan retains its rich culture and traditions, which can be seen in the numerous temples, shrines and historical quarters of Kyoto and Nara.\n" +
                    "\n" +
                    "The gastronomy of Japan is a separate journey. Taste fresh sushi at the Tsukiji Fish Market, try ramen in small cozy restaurants and enjoy the delicate taste of traditional green matcha tea. Each region of the country has its own culinary peculiarities and specialties that are definitely worth trying.\n" +
                    "\n" +
                    "The nature of Japan is also impressive in its diversity. From the snow–capped mountains of Hokkaido to the tropical beaches of Okinawa, there is something for everyone here. In spring, the whole country is immersed in the delicate pink color of sakura, and in autumn the forests are painted in bright red and gold shades. In addition, Japan is known for its hot springs (onsen), where you can relax and enjoy the magnificent scenery.\n" +
                    "\n" +
                    "The culture of etiquette is also important when traveling in Japan. The Japanese are very polite and hospitable, and tourists should respect local traditions and rules of conduct, such as cleanliness, respect for personal space and mandatory taking off their shoes when entering houses and temples. All this makes Japan one of the most unique and memorable countries to travel to.",
            countryDescriptionRu = "Япония – это удивительная страна в Восточной Азии, где современность гармонично сочетается с древними традициями. Путешественников здесь ждут высокотехнологичные города, такие как Токио и Осака, с их небоскрёбами, неоновыми вывесками и передовыми инновациями. В то же время, Япония сохраняет свою богатую культуру и традиции, которые можно увидеть в многочисленных храмах, святилищах и исторических кварталах Киото и Нары.\n" +
                    "\n" +
                    "Гастрономия Японии – это отдельное путешествие. Отведайте свежие суши на рыбном рынке Цукидзи, попробуйте рамен в маленьких уютных ресторанчиках и насладитесь тонким вкусом традиционного зелёного чая матча. В каждом регионе страны есть свои кулинарные особенности и фирменные блюда, которые обязательно стоит попробовать.\n" +
                    "\n" +
                    "Природа Японии также впечатляет своим разнообразием. От заснеженных гор Хоккайдо до тропических пляжей Окинавы – здесь найдется занятие для каждого. Весной вся страна погружается в нежно-розовый цвет сакуры, а осенью леса окрашиваются в яркие красно-золотые оттенки. Кроме того, Япония известна своими горячими источниками (онсэн), где можно расслабиться и насладиться великолепными пейзажами.\n" +
                    "\n" +
                    "В путешествии по Японии важна также культура этикета. Японцы очень вежливы и гостеприимны, и туристам стоит уважать местные традиции и правила поведения, такие как соблюдение чистоты, уважение к личному пространству и обязательное разувание при входе в дома и храмы. Всё это делает Японию одной из самых уникальных и запоминающихся стран для путешествий.",
            flagUrl = "",
            capitalId = "tokyo_id",
            population = 125_000_000,
            mainFolderName = "japan"
        ),
        USA(
            countryId = 1,
            countryNameEn = "United States of America",
            countryNameRu = "Соединенные штаты Америки",
            countryDescriptionEn = "The United States of America is a country of diverse landscapes, cultures and opportunities, ideal for travel. From bustling megacities to quiet villages, from deserts to dense forests, the USA offers its guests an incredible variety of experiences.\n" +
                    "\n" +
                    "Travelers can begin their acquaintance with America from its major cities. New York is a dynamic metropolis with world–famous landmarks such as the Statue of Liberty, Central Park and Times Square. Los Angeles, the capital of entertainment, attracts with its beaches, Hollywood and cultural mix. Chicago is famous for its architecture and music festivals, and Washington, D.C., for its historical monuments and museums.\n" +
                    "\n" +
                    "The natural beauty of the USA is also amazing. National parks such as Yellowstone, Grand Canyon and Yosemite offer spectacular views and opportunities for outdoor activities such as hiking, camping, rafting and wildlife watching. Traveling around the country, you can see a variety of landscapes – from the deserts of Arizona to the forests of Appalachia and the coastal areas of Florida.\n" +
                    "\n" +
                    "Gastronomic travel in the USA is a separate chapter. Each region offers its own culinary delights: try Chicago pizza, Texas barbecue, New England seafood and Southern cuisine with its famous fried chicken and cookies. The multinational culture of the country is reflected in the gastronomy, and here you can find restaurants with cuisines from all over the world.\n" +
                    "\n" +
                    "The USA is also famous for its hospitality and variety of cultural events. Festivals, music concerts, sporting events and fairs take place all year round, offering entertainment for every taste. Americans are proud of their heritage and traditions, and tourists are always happy to share this wealth.\n" +
                    "\n" +
                    "A visit to the USA promises to be an unforgettable adventure full of new experiences and discoveries, whether it's urban tourism, outdoor adventures or cultural research.",
            countryDescriptionRu = "Соединённые Штаты Америки – страна разнообразных ландшафтов, культур и возможностей, идеально подходящая для путешествий. От оживленных мегаполисов до тихих деревушек, от пустынь до густых лесов – США предлагают своим гостям невероятное разнообразие впечатлений.\n" +
                    "\n" +
                    "Путешественники могут начать своё знакомство с Америкой с её крупных городов. Нью-Йорк – это динамичный мегаполис с мировыми достопримечательностями, такими как Статуя Свободы, Центральный парк и Таймс-сквер. Лос-Анджелес, столица развлечений, привлекает своими пляжами, Голливудом и культурной смесью. Чикаго славится своей архитектурой и музыкальными фестивалями, а Вашингтон, округ Колумбия, – своими историческими памятниками и музеями.\n" +
                    "\n" +
                    "Природные красоты США также поражают воображение. Национальные парки, такие как Йеллоустоун, Гранд-Каньон и Йосемити, предлагают захватывающие виды и возможности для активного отдыха: походы, кемпинг, рафтинг и наблюдение за дикой природой. Путешествуя по стране, можно увидеть разнообразные пейзажи – от пустынь Аризоны до лесов Аппалачей и прибрежных зон Флориды.\n" +
                    "\n" +
                    "Гастрономические путешествия по США – это отдельная глава. Каждый регион предлагает свои кулинарные изыски: попробуйте чикагскую пиццу, техасский барбекю, морепродукты Новой Англии и южную кухню с её знаменитым фрид-чикеном и печеньем. Многонациональная культура страны нашла отражение в гастрономии, и здесь можно найти рестораны с кухнями со всего мира.\n" +
                    "\n" +
                    "США также славятся своим гостеприимством и разнообразием культурных событий. Фестивали, музыкальные концерты, спортивные мероприятия и ярмарки проходят круглый год, предлагая развлечения на любой вкус. Американцы гордятся своим наследием и традициями, и туристам всегда рады поделиться этим богатством.\n" +
                    "\n" +
                    "Визит в США обещает стать незабываемым приключением, полным новых впечатлений и открытий, будь то городской туризм, приключения на природе или культурные исследования.",
            flagUrl = "",
            capitalId = "washington_id",
            population = 333_000_000,
            mainFolderName = "usa"
        )
    }
}