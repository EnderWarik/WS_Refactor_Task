package handlers

import structures.UserInput
import kotlin.math.abs
import kotlin.random.Random

class GuessingGameHandler(private val startGuessNumber: Int, private val endGuessNumber: Int) : GameHandler
{
    private val START_MESSAGE = "Привет!\nБудешь угадывать? (да/нет)"
    private val UNDEFINED_MESSAGE = "(︶︹︺)\n непонятно, давай до свидания"
    private val NO_MESSAGE = "(×﹏×)"
    private val YES_MESSAGE = "(⌒‿⌒)"
    private val WIN_MESSAGE = "╰(▔∀▔)╯"
    private val GUESS_MESSAGE = "угадай число от $startGuessNumber до $endGuessNumber"
    private val BEYOND_GUESS_MESSAGE = "Читать не умеешь?"
    private val COLD_MESSAGE = "Холодно"
    private val WARM_MESSAGE = "Тепло"
    private val OVER_WARM_MESSAGE = "Жгётся!"

    private var isRunning = false

    // по хорошему делать через внедрение зависимостей
    private val consoleHandler = ConsoleHandler()
    override fun startGame()
    {
        isRunning = true

        guess()
        while (isRunning)
        {
            playGame()
            guess()
        }
    }

    private fun playGame()
    {
        val guessNumber = getRandomInt(startGuessNumber, endGuessNumber)
        consoleHandler.sendUserOutputLine(GUESS_MESSAGE)

        var answerNumber: Int = getAnswer()

        while (answerNumber != guessNumber)
        {
            if (answerNumber < startGuessNumber || answerNumber > endGuessNumber)
            {
                println(BEYOND_GUESS_MESSAGE)
            } else if (abs(answerNumber - guessNumber) > (startGuessNumber+endGuessNumber)/2 )
            {
                println(COLD_MESSAGE)
            } else if (abs(answerNumber - guessNumber) > (startGuessNumber+endGuessNumber)* 0.2)
            {
                println(WARM_MESSAGE)
            } else
            {
                println(OVER_WARM_MESSAGE)
            }
            answerNumber = getAnswer()
        }
        consoleHandler.sendUserOutputLine(WIN_MESSAGE)

    }

    private fun guess()
    {
        consoleHandler.sendUserOutputLine(START_MESSAGE)
        val answer = consoleHandler.getUserInputLine()

        when(answer)
        {
            UserInput.YES-> consoleHandler.sendUserOutputLine(YES_MESSAGE)
            UserInput.NO->
            {
                consoleHandler.sendUserOutputLine(NO_MESSAGE)
                stopGame()
            }
            UserInput.UNDEFINED->
            {
                consoleHandler.sendUserOutputLine(UNDEFINED_MESSAGE)
                stopGame()
            }
        }

    }
    override fun stopGame()
    {
        isRunning = false
    }

    private fun getRandomInt(begin: Int, before: Int): Int
    {
        return Random.nextInt(begin,before)
    }

    private fun getAnswer(): Int
    {
        //по хорошему сделать отдельный контруктор ошибок и "отловщик" и перенести в основной код
       return try
        {
            consoleHandler.getUserInputNumber()
        }
        catch (e: NumberFormatException)
        {
            consoleHandler.sendUserOutputLine(e.message)
            throw e
        }
    }

}