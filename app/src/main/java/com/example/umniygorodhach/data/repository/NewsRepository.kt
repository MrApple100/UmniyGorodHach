package com.example.umniygorodhach.data.repository

import android.util.Log
import com.example.traininghakatonsever.common.ResponseHandler
import com.example.umniygorodhach.data.remote.api.home.models.RaspItem
import com.example.umniygorodhach.data.remote.api.news.NewsApi
import com.example.umniygorodhach.data.remote.api.news.models.NewsItemResponse
import com.example.umniygorodhach.data.remote.api.news.models.OnlineMatch
import com.example.umniygorodhach.data.remote.api.news.models.TransItemResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val handler: ResponseHandler
) {
    //Mock Data
    val news1 = NewsItemResponse(0,"Moscow City Gazet","Спорт - это жизнь","https://api.живуспортом.рф/imagecache/full_jpeg/article_image/130/129249/5e5628e139feb.jpeg","Спорт это жизнь! Спорт укрепляет тело и дух! Таким цитатами и заголовками обычно начинается любой разговор о спорте. Я же хочу начать наш разговор другой цитатой. «Надо запретить показывать футбол по телеку, чтобы эти жирные ленивые поцы оторвали свои задницы от кресел и пошли на стадион. - Ирвин Уэлш»  Автор цитаты, кстати шотландский писатель с мировым именем! Если ты его не знаешь – стыдись! Именно он написал книги по которым потом снимались «На игле» и «Кислотный дом». А если ты не знаешь слова «поцы»… Ну во первых конечно тоже стыдись, но его значение я не буду раскрывать на страницах сего уважаемого издания. В целом эта цитата полностью раскрывает сложившиеся на данный момент взаимоотношения человечества и спорта. Все делятся на немногих спортсменов которые спортом занимаются и армию ленивых и жирных (ну вы поняли), которые могут на все это только смотреть. Заметьте! Про прекрасную половину вообще ни где ни слова! И это не справедливо. Я хочу исправить эту ситуацию и предлагаю классификацию чисто женских видов спорта. В некоторые из них вы играете, некоторые можно с удовольствием понаблюдать. Ну а некоторые приходиться наблюдать безо всякого удовольствия.",1927)
    val news2 = NewsItemResponse(1,"Питер говорит","Город на Неве","https://stihi.ru/pics/2017/09/25/8064.jpg","Спорт это жизнь! Спорт укрепляет тело и дух! Таким цитатами и заголовками обычно начинается любой разговор о спорте. Я же хочу начать наш разговор другой цитатой. «Надо запретить показывать футбол по телеку, чтобы эти жирные ленивые поцы оторвали свои задницы от кресел и пошли на стадион. - Ирвин Уэлш»  Автор цитаты, кстати шотландский писатель с мировым именем! Если ты его не знаешь – стыдись! Именно он написал книги по которым потом снимались «На игле» и «Кислотный дом». А если ты не знаешь слова «поцы»… Ну во первых конечно тоже стыдись, но его значение я не буду раскрывать на страницах сего уважаемого издания. В целом эта цитата полностью раскрывает сложившиеся на данный момент взаимоотношения человечества и спорта. Все делятся на немногих спортсменов которые спортом занимаются и армию ленивых и жирных (ну вы поняли), которые могут на все это только смотреть. Заметьте! Про прекрасную половину вообще ни где ни слова! И это не справедливо. Я хочу исправить эту ситуацию и предлагаю классификацию чисто женских видов спорта. В некоторые из них вы играете, некоторые можно с удовольствием понаблюдать. Ну а некоторые приходиться наблюдать безо всякого удовольствия.",1927)
    val news3 = NewsItemResponse(2,"Спорт.Мир.Красота","Беги за мечтой","https://smart-lab.ru/uploads/images/03/92/20/2019/11/11/1884ff.jpg","Спорт это жизнь! Спорт укрепляет тело и дух! Таким цитатами и заголовками обычно начинается любой разговор о спорте. Я же хочу начать наш разговор другой цитатой. «Надо запретить показывать футбол по телеку, чтобы эти жирные ленивые поцы оторвали свои задницы от кресел и пошли на стадион. - Ирвин Уэлш»  Автор цитаты, кстати шотландский писатель с мировым именем! Если ты его не знаешь – стыдись! Именно он написал книги по которым потом снимались «На игле» и «Кислотный дом». А если ты не знаешь слова «поцы»… Ну во первых конечно тоже стыдись, но его значение я не буду раскрывать на страницах сего уважаемого издания. В целом эта цитата полностью раскрывает сложившиеся на данный момент взаимоотношения человечества и спорта. Все делятся на немногих спортсменов которые спортом занимаются и армию ленивых и жирных (ну вы поняли), которые могут на все это только смотреть. Заметьте! Про прекрасную половину вообще ни где ни слова! И это не справедливо. Я хочу исправить эту ситуацию и предлагаю классификацию чисто женских видов спорта. В некоторые из них вы играете, некоторые можно с удовольствием понаблюдать. Ну а некоторые приходиться наблюдать безо всякого удовольствия.",1927)
    val news4 = NewsItemResponse(3,"Братск может!","Вдали от цивилизации","https://mtdata.ru/u1/photoA18C/20378118981-0/original.jpg","Спорт это жизнь! Спорт укрепляет тело и дух! Таким цитатами и заголовками обычно начинается любой разговор о спорте. Я же хочу начать наш разговор другой цитатой. «Надо запретить показывать футбол по телеку, чтобы эти жирные ленивые поцы оторвали свои задницы от кресел и пошли на стадион. - Ирвин Уэлш»  Автор цитаты, кстати шотландский писатель с мировым именем! Если ты его не знаешь – стыдись! Именно он написал книги по которым потом снимались «На игле» и «Кислотный дом». А если ты не знаешь слова «поцы»… Ну во первых конечно тоже стыдись, но его значение я не буду раскрывать на страницах сего уважаемого издания. В целом эта цитата полностью раскрывает сложившиеся на данный момент взаимоотношения человечества и спорта. Все делятся на немногих спортсменов которые спортом занимаются и армию ленивых и жирных (ну вы поняли), которые могут на все это только смотреть. Заметьте! Про прекрасную половину вообще ни где ни слова! И это не справедливо. Я хочу исправить эту ситуацию и предлагаю классификацию чисто женских видов спорта. В некоторые из них вы играете, некоторые можно с удовольствием понаблюдать. Ну а некоторые приходиться наблюдать безо всякого удовольствия.",1927)
    val news5 = NewsItemResponse(4,"Танцеры России","Танцы,танцы и еще раз танцы","https://sportishka.com/uploads/posts/2022-03/thumbs/1648647399_37-sportishka-com-p-krasivie-sovremennie-tantsi-sport-krasivie-52.jpg","Спорт это жизнь! Спорт укрепляет тело и дух! Таким цитатами и заголовками обычно начинается любой разговор о спорте. Я же хочу начать наш разговор другой цитатой. «Надо запретить показывать футбол по телеку, чтобы эти жирные ленивые поцы оторвали свои задницы от кресел и пошли на стадион. - Ирвин Уэлш»  Автор цитаты, кстати шотландский писатель с мировым именем! Если ты его не знаешь – стыдись! Именно он написал книги по которым потом снимались «На игле» и «Кислотный дом». А если ты не знаешь слова «поцы»… Ну во первых конечно тоже стыдись, но его значение я не буду раскрывать на страницах сего уважаемого издания. В целом эта цитата полностью раскрывает сложившиеся на данный момент взаимоотношения человечества и спорта. Все делятся на немногих спортсменов которые спортом занимаются и армию ленивых и жирных (ну вы поняли), которые могут на все это только смотреть. Заметьте! Про прекрасную половину вообще ни где ни слова! И это не справедливо. Я хочу исправить эту ситуацию и предлагаю классификацию чисто женских видов спорта. В некоторые из них вы играете, некоторые можно с удовольствием понаблюдать. Ну а некоторые приходиться наблюдать безо всякого удовольствия.",1927)

    val listnews = arrayListOf<NewsItemResponse>(news1,news2,news3,news4,news5)


    suspend fun fetchNews() = handler{
      // val list = newsApi.getNews() as MutableList<NewsItemResponse>
       // list
        listnews
    }

    val trans = TransItemResponse(0,"Шахматы","https://www.ofsi.ru/upload/iblock/5e3/64e1ojzwfeu1fkdm0od1a7sibjvk8p5s/1393066_1.jpg","https://www.youtube.com/watch?v=x1NOF_fm7ZY")
    val trans2 = TransItemResponse(1,"Лыжи","https://vyborok.com/wp-content/uploads/2019/12/csm_langlaufen_obertauern5_be97ef4c01_04438900.jpg","https://www.youtube.com/watch?v=yoDQmBQL6NQ")
    val trans3 = TransItemResponse(2,"Бокс","https://wikifight.ru/wp-content/uploads/2021/11/https-avatars-mds-yandex-net-get-zen_doc-2417275.jpeg","https://www.youtube.com/watch?v=V_5Foyce6BE")
    val trans4 = TransItemResponse(3,"Скейт","https://img2.akspic.ru/attachments/crops/4/0/2/0/20204/20204-longbord-skejtbord-zanyatie_sportom-skejtbordist-otdyx-2560x1440.jpg","https://www.youtube.com/watch?v=HlFFpirWyfA")
    val trans5 = TransItemResponse(4,"Коньки","https://rf-smi.ru/uploads/posts/2022-09/1663599162_5142214.jpg","https://www.youtube.com/watch?v=adYbPIODPj0")
    val trans6 = TransItemResponse(5,"Страйкбол","https://aif-s3.aif.ru/images/023/932/fc0e78f9cb73a91c77c9ded7339509e7.jpg","https://www.youtube.com/watch?v=E3GGqjh93d0")
    val trans7 = TransItemResponse(6,"Гонки","https://images.genius.com/8a7bad4041e07bd55e68578161bd7a15.1000x668x1.jpg","https://www.youtube.com/watch?v=oFZnmNPRoNc")
    val trans8 = TransItemResponse(7,"Футбол","https://wallup.net/wp-content/uploads/2019/09/335834-arsenal-chelsea-soccer.jpg","https://www.youtube.com/watch?v=U97VkjEdUZ4")

    val listtrans = arrayListOf(trans,trans2,trans3,trans4,trans5,trans6,trans7,trans8)


    suspend fun fetchTrans() = handler{
        // val list = newsApi.getTrans() as MutableList<TransItemResponse>
       // list
        listtrans
    }

    val onlineMatch = OnlineMatch("ЦСКА","Динамо","2","1","Футбол","Полуфинал СуперКубка","https://www.youtube.com/watch?v=Yb-NiQb0D_k")

    suspend fun fetchOnlineMatch() = handler{
        //newsApi.getOnlineMatch()
        onlineMatch
    }

}