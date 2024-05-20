package eryaz.software.panda.data.persistence

import eryaz.software.panda.data.models.dto.WorkActionDto
import eryaz.software.panda.data.models.dto.WorkActivityDto
import eryaz.software.panda.data.models.remote.response.WorkActionTypeResponse

class TemporaryCashManager private constructor() {
    var workActionTypeList:List<WorkActionTypeResponse>? = null
    var workActivity: WorkActivityDto? = null
    var workAction: WorkActionDto? = null

    companion object {
        @Volatile
        private var instance: TemporaryCashManager? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: TemporaryCashManager().also { instance = it }
            }
    }
}