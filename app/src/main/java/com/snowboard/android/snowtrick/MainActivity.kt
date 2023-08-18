package com.snowboard.android.snowtrick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ToggleButton
import com.snowboard.android.snowtrick.SnowboardTrick.Companion.allGrabs
import com.snowboard.android.snowtrick.SnowboardTrick.Companion.grabsEasy
import com.snowboard.android.snowtrick.SnowboardTrick.Companion.grabsMedium
import com.snowboard.android.snowtrick.SnowboardTrick.Companion.grabsHard
import com.snowboard.android.snowtrick.animation.RotationAnimation
import com.snowboard.android.snowtrick.animation.checkAndGetAnimation
import com.snowboard.android.snowtrick.databinding.ActivityMainBinding
import com.snowboard.android.snowtrick.fragment.CheckDifficultyFragment
import com.snowboard.android.snowtrick.fragment.CheckRotationFragment
import com.snowboard.android.snowtrick.fragment.DescriptionTrick
import com.snowboard.android.snowtrick.fragment.getGrabFromDialog

class MainActivity : AppCompatActivity(),
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

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for ActivityMainBinding " +
                "must not be null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        экземпляр анимаций
        val animationForBoard = RotationAnimation()

//        определяем стойку в tbtnStance
        val stanceButton: ToggleButton = binding.tbtnStance
        stanceButton.setOnCheckedChangeListener { _, isChecked ->
            stance = if (isChecked) Stance.Regular else Stance.Goofy
//            println(stance)
        }

        showTrickField = binding.tvShowTrick

//        Жмем по btnShowButton и получаем трюк
        showTrickButton = binding.btnShowButton
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
            animationForBoard.getFade(showTrickField)
        }

//          Показываем alertDialog - Rotation
        showMenuButton = binding.ibtnShowRotation

        showMenuButton.setOnClickListener {
            val checkRotationFragment = CheckRotationFragment()
            checkRotationFragment.show(supportFragmentManager, "show_rotation")
        }

//        Показываем alertDialog - Difficulty
        showDifficultyButton = binding.ibtnShowDifficulty

        showDifficultyButton.setOnClickListener {
            val difficultyFragment = CheckDifficultyFragment()
            difficultyFragment.show(supportFragmentManager, "show_difficulty")
        }

//        нажимая на картинку борда начинаем вращать ее (анимация вращения)
        snowboardingImg = binding.ivSnowboard
        snowboardingImg.setOnClickListener {

    //        проверяем и воспроизводим анимацию в зависимости от направления вращения (direction)
            checkAndGetAnimation(showTrickField.text, stance)
//            println("check point")
        }
    }

//    переопределяем функцию выбранных элементов в диалоге rotation и присваиваем их нашей переменной
    override fun onSelectedItemsRotation(selectedItems: ArrayList<Int>) {
        fromListDialogRotation = selectedItems
    }

//    переопределяем функцию выбранных элементов в диалоге difficulty и присваиваем их нашей переменной
    override fun onSelectedItemsDifficulty(selectedItems: ArrayList<Int>) {
        fromListDialogDifficulty = selectedItems
    }

    private fun showDescriptionTrick() {
        val description = DescriptionTrick()
        description.show(supportFragmentManager, "description")
    }
}