package com.snowboard.android.snowtrick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.fragment.app.DialogFragment

class MainActivity : AppCompatActivity(),
    MyDialogFragment.MyDialogListener, MyDialogFragment.OnDialogSelectedItems,
    CheckDifficultyFragment.OnDialogSelectedItems {

//    Два списка для возврата данных из диалогов
    var fromListDialog: ArrayList<Int> = arrayListOf()
    var fromListDialogDifficulty: ArrayList<Int> = arrayListOf()

//    Переменные для кнопок и текст. полей ну и прочего
    private lateinit var showTrickButton: Button
    private lateinit var showDifficultyButton: ImageButton
    private lateinit var showMenuButton: ImageButton
    private lateinit var showTrickView: TextView
    private lateinit var snowboardImg: ImageView


//    Экзепляры проверяющих трюк классов
    private var trick = SnowTricks()
    private var trickDifficulty = DifficultyTrick()

    private var stance = "goofy"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//Жмем по кнопке и получаем трюк
        showTrickView = findViewById(R.id.show_trick_view)
        showTrickButton = findViewById(R.id.show_button)
        showTrickButton.setOnClickListener {

//            Проверяем, что вернулось из MyDialogFragment
            val (trickOneEighty: Boolean,
                trickThreeSixty: Boolean,
                trickGrabs: Boolean) = trick.tripleRotation(fromListDialog)

//            Проверяем, что вернулось из CheckDifficultyFragment
            val (easyTrick: Boolean,
                mediumTrick: Boolean,
                hardTrick: Boolean) = trickDifficulty.tripleDifficulty(fromListDialogDifficulty)

//            Вызываем функцию для проверки сложности трюка
            val checkDifficultyTrick = trickDifficulty.checkDifficultyGrab(
                checkEasyTrick = easyTrick,
                checkMediumTrick = mediumTrick,
                checkHardTrick = hardTrick
            )

//            Вызываем функцию для проверки списка трюков
            val checkRandomTrick = trick.checkTrick(
                checkOneEighty = trickOneEighty,
                checkThreeSixty = trickThreeSixty,
                checkGrabs = trickGrabs
            )

//            Жмем на кнопку и передаем название трюка, ротацию и сложность в textView
            val showTextResId = trick.getRandomTrickAll(
                rotation = checkRandomTrick,
                trick = checkDifficultyTrick
            ).toString()
            showTrickView.text = showTextResId
        }
//          Показываем алерт
        showMenuButton = findViewById(R.id.show_menu)
        showMenuButton.setOnClickListener {
            val myDialogFragment = MyDialogFragment()
            myDialogFragment.show(supportFragmentManager, "show_menu")
        }
//        Кнопка сложности, вместе с показам алертДиалога
        showDifficultyButton = findViewById(R.id.show_difficulty)
        showDifficultyButton.setOnClickListener {
            val difficultyFragment = CheckDifficultyFragment()
            difficultyFragment.show(supportFragmentManager, "show_difficulty")
        }

//        переключение стойки
        val stanceButton: ToggleButton = findViewById(R.id.stance_button)
        stanceButton.setOnCheckedChangeListener { _, isChecked ->
            stance = if (isChecked) "regular" else "goofy"
        }

//        нажимая на картинку борда начинаем вращать ее (анимация вращения)
        snowboardImg = findViewById(R.id.snow_img)
        snowboardImg.setOnClickListener {
//            подгружаем анимации в переменные (по часовой и против часовой стрелки)
            val clickClockWiseRotate = AnimationUtils.loadAnimation(
                this,
                R.anim.rotate_clockwise
            )
            val clickAntiClockWise = AnimationUtils.loadAnimation(
                this,
                R.anim.rotate_anticlockwise
            )
//            воспроизводим анимацию в зависимости от направления вращения (direction)
            when {
                "FS" in showTrickView.text && stance == "goofy" -> snowboardImg.startAnimation(clickClockWiseRotate)
                "BS" in showTrickView.text && stance == "goofy" -> snowboardImg.startAnimation(clickAntiClockWise)
                "BS" in showTrickView.text && stance == "regular" -> snowboardImg.startAnimation(clickClockWiseRotate)
                "FS" in showTrickView.text && stance == "regular" -> snowboardImg.startAnimation(clickAntiClockWise)
            }
        }
    }


    //    Назначаем перезаписью действие для кнопки "ОК"
    override fun onDialogPositiveClick(dialog: DialogFragment) {

    }

//    Назначаем перезаписью действие для кнопки "Cancel"
    override fun onDialogNegativeClick(dialog: DialogFragment) {
        //надо сделать отмену (вроде сделал, надо убрать коммент)
    }

//    переопределяем функцию выбранных элементов в диалоге rotation и присваиваем их нашей переменной
    override fun onSelectedItems(selectedItems: ArrayList<Int>) {
        fromListDialog = selectedItems
    }
//    переопределяем функцию выбранных элементов в диалоге difficulty и присваиваем их нашей переменной
    override fun onSelectedItemsDifficulty(selectedItems: ArrayList<Int>) {
        fromListDialogDifficulty = selectedItems
    }

}