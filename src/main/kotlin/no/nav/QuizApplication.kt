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
            "NAV" -> handleNavQuiz(question)
            else -> {
                print(question.category)
            }
        }
    }

    private fun handleNavQuiz(question: Question) {
        var svar = ""
        when (question.question) {
            "På hvilken nettside finner man informasjon om rekruttering til NAV IT?" -> svar = "https://www.detsombetyrnoe.no/"
            "Hva heter applikasjonsplattformen til NAV?" -> svar = "NAIS"
        }


        answer(question.category, question.messageId, svar)
    }

    /*
    [Question] category: NAV, question: På hvilken nettside finner man informasjon om rekruttering til NAV IT?, id: a9cca2be-3481-4a99-ad8b-70a21459d166

    NAV{"@timestamp":"2022-06-23T15:05:24.939+02:00","@version":"1","message":"[Question] category: NAV, question: Hva heter applikasjonsplattformen til NAV?, id: ace67771-0d3b-46de-bb9c-9f40a943b5b0","logger_name":"kafkaesque","thread_name":"main","level":"INFO","level_value":20000}
     */

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