package com.snowboard.android.snowtrick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ToggleButton
import androidx.fragment.app.DialogFragment
import com.snowboard.android.snowtrick.SnowboardTrick.Companion.allGrabs
import com.snowboard.android.snowtrick.SnowboardTrick.Companion.grabsEasy
import com.snowboard.android.snowtrick.SnowboardTrick.Companion.grabsMedium
import com.snowboard.android.snowtrick.SnowboardTrick.Companion.grabsHard

class MainActivity : AppCompatActivity(),
    CheckRotationFragment.MyDialogListener, CheckRotationFragment.OnDialogSelectedItems,
    CheckDifficultyFragment.OnDialogSelectedItems {

//    Два списка для возврата данных из диалогов
    private var fromListDialogRotation: ArrayList<Int> = arrayListOf()
    private var fromListDialogDifficulty: ArrayList<Int> = arrayListOf()

//    Переменные для кнопок и текст. полей ну и прочего
    private lateinit var showTrickButton: Button
    private lateinit var showDifficultyButton: ImageButton
    private lateinit var showMenuButton: ImageButton
    lateinit var showTrickView: TextView
    private lateinit var showTextResId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        экэемпляр анимаций
        val animatorBoard = RotationAnimation()


//        переключение стойки
        val stanceButton: ToggleButton = findViewById(R.id.stance_button)
        stanceButton.setOnCheckedChangeListener { _, isChecked ->
            stance = if (isChecked) Stance.Regular else Stance.Goofy
            println(stance)
        }

//Жмем по кнопке и получаем трюк
        showTrickView = findViewById(R.id.show_trick_view)
        showTrickButton = findViewById(R.id.show_button)
        showTrickButton.setOnClickListener {
//            println(fromListDialogDifficulty)

            if (fromListDialogRotation.isNotEmpty()) rotation.clear()
            fromListDialogRotation.forEach {
                when (it) {
                    0 -> rotation += Rotation.Zero.id
                    1 -> rotation += Rotation.OneEighty.id
                    2 -> rotation += Rotation.ThreeSixty.id
                }
            }

            // Если список difficulty не пустой, то чистим список allGrabs и добавляем в него
            // те грэбы, которые были отмеченны в difficulty
            if (fromListDialogDifficulty.isNotEmpty()) allGrabs = arrayOf()

            fromListDialogDifficulty.forEach {
                when (it) {
                    0 -> allGrabs += grabsEasy
                    1 -> allGrabs += grabsMedium
                    2 -> allGrabs += grabsHard
                    }
                }

            showTrickView.text = allGrabs.flatten().random().getTrick()

//            запуск анимации появления из тени для текста трюка
            animatorBoard.getFade(showTrickView)

        }
//          Показываем алерт
        showMenuButton = findViewById(R.id.show_menu)
        showMenuButton.setOnClickListener {

            val checkRotationFragment = CheckRotationFragment()
            checkRotationFragment.show(supportFragmentManager, "show_menu")
        }
//        Кнопка сложности, вместе с показам алертДиалога
        showDifficultyButton = findViewById(R.id.show_difficulty)
        showDifficultyButton.setOnClickListener {
            val difficultyFragment = CheckDifficultyFragment()
            difficultyFragment.show(supportFragmentManager, "show_difficulty")
        }

//        нажимая на картинку борда начинаем вращать ее (анимация вращения)
        snowboardingImg = findViewById(R.id.snow_img)
        snowboardingImg.setOnClickListener {

            // ВОТ ЭТО НАДО ПЕРЕДЕЛАТЬ
//            воспроизводим анимацию в зависимости от направления вращения (direction)
            when {
                "FS" in showTrickView.text && stance == Stance.Goofy && "180" in showTrickView.text   -> animatorBoard.getOneEightyClockWise()
                "BS" in showTrickView.text && stance == Stance.Goofy && "180" in showTrickView.text   -> animatorBoard.getOneEightyAntiClockWise()
                "BS" in showTrickView.text && stance == Stance.Regular && "180" in showTrickView.text -> animatorBoard.getOneEightyClockWise()
                "FS" in showTrickView.text && stance == Stance.Regular && "180" in showTrickView.text -> animatorBoard.getOneEightyAntiClockWise()
                "FS" in showTrickView.text && stance == Stance.Goofy && "360" in showTrickView.text   -> animatorBoard.getThreeSixtyClockWise()
                "BS" in showTrickView.text && stance == Stance.Goofy && "360" in showTrickView.text   -> animatorBoard.getThreeSixtyAntiClockWise()
                "BS" in showTrickView.text && stance == Stance.Regular && "360" in showTrickView.text -> animatorBoard.getThreeSixtyClockWise()
                "FS" in showTrickView.text && stance == Stance.Regular && "360" in showTrickView.text -> animatorBoard.getThreeSixtyAntiClockWise()
            }
        }
    }

                    // ТУТ НАДО ПЕРЕБРАТЬ ВСЕ ИЛИ ХОТЯ БЫ ЧАСТЬ
                    // это очень важно, даже капсом написал!

    //    Назначаем перезаписью действие для кнопки "ОК"
    override fun onDialogPositiveClick(dialog: DialogFragment) {

    }

//    Назначаем перезаписью действие для кнопки "Cancel"
    override fun onDialogNegativeClick(dialog: DialogFragment) {
        //надо сделать отмену (вроде сделал, надо убрать коммент)
    }

//    переопределяем функцию выбранных элементов в диалоге rotation и присваиваем их нашей переменной
    override fun onSelectedItems(selectedItems: ArrayList<Int>) {
        fromListDialogRotation = selectedItems
    }
//    переопределяем функцию выбранных элементов в диалоге difficulty и присваиваем их нашей переменной
    override fun onSelectedItemsDifficulty(selectedItems: ArrayList<Int>) {
        fromListDialogDifficulty = selectedItems
    }

}