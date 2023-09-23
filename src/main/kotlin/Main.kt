import handlers.GameHandler
import handlers.GuessingGameHandler

fun main()
{
 val guessingGameHandler: GameHandler = GuessingGameHandler(1,10)
 guessingGameHandler.startGame()
}

