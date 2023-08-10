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
    CheckRotationFragment.MyDialogListener,
    CheckRotationFragment.OnDialogSelectedItems,
    CheckDifficultyFragment.OnDialogSelectedItems {

//    Два списка для возврата данных из диалогов
    private var fromListDialogRotation: ArrayList<Int> = arrayListOf()
    private var fromListDialogDifficulty: ArrayList<Int> = arrayListOf()

//    Переменные для кнопок и текст. Полей ну и прочего
    lateinit var showTrickButton: Button
    private lateinit var showDifficultyButton: ImageButton
    private lateinit var showMenuButton: ImageButton
    lateinit var showTrickField: TextView
    private lateinit var showTextResId: String
    lateinit var getGrab: SnowboardTrick


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        экземпляр анимаций
        val animatorBoard = RotationAnimation()

//        переключение стойки
        val stanceButton: ToggleButton = findViewById(R.id.stance_button)
        stanceButton.setOnCheckedChangeListener { _, isChecked ->
            stance = if (isChecked) Stance.Regular else Stance.Goofy
            println(stance)
        }

//        Жмем по кнопке и получаем трюк
        showTrickField = findViewById(R.id.show_trick_view)
        showTrickButton = findViewById(R.id.show_button)
        showTrickButton.setOnClickListener {
//            println(fromListDialogDifficulty)

            // Если список fromListDialogRotation не пустой, то чистим список rotation
            // и добавляем в него выбранные пользователем вращения.
            if (fromListDialogRotation.isNotEmpty()) rotation.clear()
            fromListDialogRotation.forEach {
                when (it) {
                    0 -> rotation += Rotation.Zero.id
                    1 -> rotation += Rotation.OneEighty.id
                    2 -> rotation += Rotation.ThreeSixty.id
                }
            }

            // Если список fromListDialogDifficulty не пустой, то чистим список allGrabs
            // и добавляем в него те грэбы, которые были отмечены пользователем.
            if (fromListDialogDifficulty.isNotEmpty()) allGrabs = arrayOf()
            fromListDialogDifficulty.forEach {
                when (it) {
                    0 -> allGrabs += grabsEasy
                    1 -> allGrabs += grabsMedium
                    2 -> allGrabs += grabsHard
                    }
                }

//            тут генерируем трюк и показываем его в текстовом поле
//            так же прослушиваем тап по текстовому полю, выводим описание

            getGrab = allGrabs.flatten().random()
            getGrabFromDialog = getGrab

            showTrickField.text = getGrab.showTrick()
            showTrickField.setOnClickListener {
                showDescriptionTrick()
            }

//            запуск анимации появления из тени для текста трюка
            animatorBoard.getFade(showTrickField)

        }
//          Показываем alertDialog - Rotation
        showMenuButton = findViewById(R.id.show_menu)

        showMenuButton.setOnClickListener {
            val checkRotationFragment = CheckRotationFragment()
            checkRotationFragment.show(supportFragmentManager, "show_rotation")
        }
//        Показываем alertDialog - Difficulty
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

            val goofyFS180 = "FS" in showTrickField.text && stance == Stance.Goofy && "180" in showTrickField.text
            when {
                 goofyFS180  -> animatorBoard.getOneEightyClockWise() // можно использовать это для рефакторинга, но вариант тоже так себе
                "BS" in showTrickField.text && stance == Stance.Goofy && "180" in showTrickField.text   -> animatorBoard.getOneEightyAntiClockWise()
                "BS" in showTrickField.text && stance == Stance.Regular && "180" in showTrickField.text -> animatorBoard.getOneEightyClockWise()
                "FS" in showTrickField.text && stance == Stance.Regular && "180" in showTrickField.text -> animatorBoard.getOneEightyAntiClockWise()
                "FS" in showTrickField.text && stance == Stance.Goofy && "360" in showTrickField.text   -> animatorBoard.getThreeSixtyClockWise()
                "BS" in showTrickField.text && stance == Stance.Goofy && "360" in showTrickField.text   -> animatorBoard.getThreeSixtyAntiClockWise()
                "BS" in showTrickField.text && stance == Stance.Regular && "360" in showTrickField.text -> animatorBoard.getThreeSixtyClockWise()
                "FS" in showTrickField.text && stance == Stance.Regular && "360" in showTrickField.text -> animatorBoard.getThreeSixtyAntiClockWise()
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
    override fun onSelectedItemsRotation(selectedItems: ArrayList<Int>) {
        fromListDialogRotation = selectedItems
    }
//    переопределяем функцию выбранных элементов в диалоге difficulty и присваиваем их нашей переменной
    override fun onSelectedItemsDifficulty(selectedItems: ArrayList<Int>) {
        fromListDialogDifficulty = selectedItems
    }

    fun showDescriptionTrick() {
        val description = DescriptionTrick()

        description.show(supportFragmentManager, "description")
    }

}