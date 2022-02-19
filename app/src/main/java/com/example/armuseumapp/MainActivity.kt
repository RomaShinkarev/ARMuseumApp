package com.example.armuseumapp


import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.Manifest
import android.util.Log

class MainActivity : AppCompatActivity() {
    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    var buttonClick: Button? = null

    public val imageList = listOf<Image>(
        Image(
            R.drawable.img1,
            "Полевой телефон времён ВОВ",
            "Это вид телефона, предназначенный для эксплуатации особых условиях и обладающий большой мобильностью в эксплуатации. В первую очередь разрабатывался для организации связи в полевых условиях. Один из них принадлежал Поганкову Алкександру Ивановичу."
        ),
        Image(
            R.drawable.img2,
            "Фотоаппарат советского воздушного разведчика",
            "Фотоаппарат принадлежал Кузнецову Степану Дмитриевичу - герою Советского союза. Был подарен музею его внуком Кузнецовым Денисом в 1997."
        ),

        Image(
            R.drawable.img3,
            "Армейский телеграфный ключ ТК - 1",
            "Телеграф - средство передачи сигнала по проводам, радио или другим каналам. Передачу информации телеграфным способом называют телеграфией."
        ),

        Image(
            R.drawable.img4,
            "Компас",
            "Компас - устройство, облегчающее ориентирование на местности путём указания на магнитные полюса Земли и стороны света."
        ),

        Image(
            R.drawable.img5,
            "Медаль «20 лет Победы ВОВ»",
            "Учреждена: Указом Президиума Верх. Совета от 7 мая 1995; Событие: годовщина 20 лет победы в ВОВ."
        ),

        Image(
            R.drawable.img6,
            "Медаль «30 лет Победы ВОВ»",
            "Учреждена: Указом Президиума Верх. Совета от 25 апреля 1975; Событие: годовщина 30 лет победы в ВОВ."
        ),

        Image(
            R.drawable.img7,
            "Медаль «40 лет Победы ВОВ»",
            "Учреждена: Указом Президиума Верх. Совета от 12 апреля 1985; Событие: годовщина 40 лет победы в ВОВ."
        ),

        Image(
            R.drawable.img8,
            "Армейский сигнальный фонарь времён ВОВ",
            "Фонарь - один из элементов экипировки офицеров, солдат разведывательных частей времен Второй мировой войны. С помощью фонаря солдаты не только освещали дорогу, но и могли подавать сигналы."
        ),

        Image(
            R.drawable.img9,
            "Военный бинокль времен ВОВ",
            "Бинокль — это оптический прибор, благодаря которому можно вести наблюдение за объектами, удаленными на большое расстояние."
        ),

        Image(
            R.drawable.img10,
            "Выходная военная форма артиллериста",
            "Выходная военная форма — это форма предназначенная для выходных мероприятий (представление к награде и т.д.)."
        ),
        Image(
            R.drawable.img11,
            "Оптический прибор ПГ - 1М",
            "Визирный и угломерный оптический прибор наземной артиллерии и реактивных установок залпового огня. Обеспечивает наведение орудия в вертикальной и горизонтальной плоскостях. Применяется при стрельбе как по видимым, так и по невидимым целям."
        ),
        Image(
            R.drawable.img12,
            "Пулеметная лента",
            "Специальная лента, снаряжённая патронами для боевого питания стрелкового оружия, обычно - пулемёта, как правило находящаяся в патронной коробке, но также автоматических пушек и гранатомётов."
        ),
        Image(
            R.drawable.img13,
            "ГП - 7",
            "Гражданский противогаз. Прототип маски МГП из комплекта ГП-7 была изготовлена в 1979 году на ЭНПО 'Неорганика' , а прототип фильтра - ГП-7К - МКГ датирован 2-ым кварталом 1980 года."
        ),
        Image(
            R.drawable.img14,
            "Каска",
            "Штальхельм - пехотная каска, которая под этим названием ассоциируется прежде всего с вооружёнными силами Германии с 1916 по 1945 год. Заменив во время Первой мировой войны пикельхельм, позже штальхельм стал одним из наиболее узнаваемых атрибутов солдат Вермахта."
        ),
        Image(
            R.drawable.img15,
            "Граната Ф-1",
            "Ф-1 (в просторечии — «фенюша» или «лимонка») — ручная оборонительная граната. Граната предназначена для поражения живой силы в оборонительном бою. Из-за значительного радиуса разлёта осколков (до 150 метров) метать её можно только из укрытия, бронетранспортёра или танка."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonClick = findViewById(R.id.qr_button)
        buttonClick?.setOnClickListener {
            checkCameraPermission()
        }

        val recyclerView = findViewById<RecyclerView>(R.id._imageRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ImageAdapter(this, imageList) {
            val image = intent.getParcelableExtra<Image>(INTENT_PARCELABLE)
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }
    }

    private fun checkCameraPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 12)
        }
        else{
            val intent = Intent(this, ScannerActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 12){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(this, ScannerActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
