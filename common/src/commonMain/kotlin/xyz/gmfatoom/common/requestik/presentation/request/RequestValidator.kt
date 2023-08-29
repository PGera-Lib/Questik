package xyz.gmfatoom.common.requestik.presentation.request

import xyz.gmfatoom.common.requestik.domain.model.RequestModel
import xyz.gmfatoom.common.utils.checkInputDatePatternBefore

object RequestValidator {

    fun validateRequest(request: RequestModel): ValidationResult {
        var result = ValidationResult()

        if (request.name.isBlank()) {
            result = result.copy(nameError = "Наименование задачи не может быть сохранено пустым.")
        }

        if (request.user_creator?.fullname.isNullOrBlank()) {
            result = result.copy(userCreatorError = "Автор заявки не может быть пуст")
        }

        val dataTimeRegex = Regex("(3[01]|[12][0-9]|0[1-9]|[1-9])[\\-](1[0-2]|0[1-9]|[1-9])[\\-][0-9]{4}[\\s][0-9][0-9]:([0]?[0-5][0-9]|[0-9])\$")
        if(!dataTimeRegex.matches(request.data_create)) {
            result = result.copy(dataCreateError = "Не корректная дата создания заявки. Формат 28-02-2022 12:12 (dd-mm-yyyy hh:mm)")
        }
    /*    if(!dataTimeRegex.matches(request.data_start)) {
            result = result.copy(dataStartError = "Не корректная дата выполнения заявки. Формат 28-02-2022 12:12 (dd-mm-yyyy hh:mm)")
        }
        if(!dataTimeRegex.matches(request.data_end)) {
            result = result.copy(dataEndError = "Не корректная дата завершения заявки. Формат 28-02-2022 12:12 (dd-mm-yyyy hh:mm)")
        }*/


        return result
    }


    data class ValidationResult(
        val nameError: String? = null,
        val userCreatorError: String? = null,
        val dataCreateError: String? = null,
        val dataStartError: String? = null,
        val dataEndError: String? = null,
    )
}


