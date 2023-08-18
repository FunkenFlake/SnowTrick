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
import com.snowboard.android.snowtrick.animation.RotationAnimation
import com.snowboard.android.snowtrick.animation.checkAndGetAnimation
import com.snowboard.android.snowtrick.fragment.CheckDifficultyFragment
import com.snowboard.android.snowtrick.fragment.CheckRotationFragment
import com.snowboard.android.snowtrick.fragment.DescriptionTrick
import com.snowboard.android.snowtrick.fragment.getGrabFromDialog

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
        val stanceButton: ToggleButton = findViewById(R.id.tbtnStance)
        stanceButton.setOnCheckedChangeListener { _, isChecked ->
            stance = if (isChecked) Stance.Regular else Stance.Goofy
//            println(stance)
        }

//        Жмем по кнопке и получаем трюк
        showTrickField = findViewById(R.id.tvShowTrick)
        showTrickButton = findViewById(R.id.btnShowButton)
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
            getGrab = allGrabs.flatten().shuffled().last()
//            println(getGrab)
            getGrabFromDialog = getGrab

//            прослушиваем тап по текстовому полю, выводим описание
            showTrickField.text = getGrab.showTrick()
            showTrickField.setOnClickListener {
                showDescriptionTrick()
            }

//            запуск анимации появления из тени для текста трюка
            animatorBoard.getFade(showTrickField)
        }

//          Показываем alertDialog - Rotation
        showMenuButton = findViewById(R.id.ibtnShowMenu)

        showMenuButton.setOnClickListener {
            val checkRotationFragment = CheckRotationFragment()
            checkRotationFragment.show(supportFragmentManager, "show_rotation")
        }

//        Показываем alertDialog - Difficulty
        showDifficultyButton = findViewById(R.id.ibtnShowDifficulty)

        showDifficultyButton.setOnClickListener {
            val difficultyFragment = CheckDifficultyFragment()
            difficultyFragment.show(supportFragmentManager, "show_difficulty")
        }

//        нажимая на картинку борда начинаем вращать ее (анимация вращения)
        snowboardingImg = findViewById(R.id.ivSnowboard)
        snowboardingImg.setOnClickListener {

    //        проверяем и воспроизводим анимацию в зависимости от направления вращения (direction)
            checkAndGetAnimation(showTrickField.text, stance)
//            println("check point")
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