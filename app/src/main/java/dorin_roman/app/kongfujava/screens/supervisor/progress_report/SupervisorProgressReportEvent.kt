package dorin_roman.app.kongfujava.screens.supervisor.progress_report

import dorin_roman.app.kongfujava.screens.supervisor.StudentModel

sealed class SupervisorProgressReportEvent {
    class UpdateSelectedStudent(val student: StudentModel) : SupervisorProgressReportEvent()
    class UpdateSelectedLevelInfo(val levelId: Int) : SupervisorProgressReportEvent()
    class UpdateSelectedOverallStats(val index: Int) : SupervisorProgressReportEvent()
}