package no.nav

import no.nav.db.Database
import no.nav.quizrapid.*
import no.nav.rapid.Assessment
import no.nav.rapid.Question


/**
 * QuizApplication
 *
 * Her skal teamet bygge ut funksjonalitet for å løse oppgavene i leesah-game.
 */
class QuizApplication(private val teamName: String, database: Database? = null): QuizParticipant(teamName) {

    override fun handle(question: Question) {
        logger.log(question)

        when(question.category) {
            "team-registration" -> handleRegisterTeam(question)
            "arithmetic" -> handleArithmetic(question)
            "make-ingress" -> handleMakeIngress(question)
            else -> {
                print(question.category)
            }
        }
    }

    private fun handleMakeIngress(question: Question) {
        val ingress = "https://kafkaesque.dev.intern.nav.no"
        answer(question.category, question.messageId, ingress)
    }

    private fun handleArithmetic(question: Question) {
        val l = question.question.split(" ")

        val tall1 = l[0].toInt()
        val tall2 = l[2].toInt()

        var result = 0

        when(l[1]) {
            "-" -> result = tall1 - tall2
            "+" -> result = tall1 + tall2
            "*" -> result = tall1 * tall2
            "/" -> result = tall1 / tall2
        }

        answer(question.category, question.messageId, result.toString())
    }


    override fun handle(assessment: Assessment) {
        logger.log(assessment)
    }

    /**
     * Spørsmål handlers
     */

    private fun handleRegisterTeam(question: Question) {
        answer(question.category, question.messageId, teamName)
    }

}