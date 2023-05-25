package lt.vgrabauskas.homework07


import java.time.LocalDateTime

data class Note(
    val id: Int,
    var name: String,
    var details: String,
    var creationDate: LocalDateTime = LocalDateTime.now(),
    var updateDate: LocalDateTime = LocalDateTime.now()
) {
//    var id = this._id
//        private set
//
//    var name: String
//        get():String {
//            return _name
//        }
//        set(value) {
//            this._name = value
//            this._updateDate = LocalDateTime.now()
//        }
//
//    var details: String
//        get():String {
//            return _details
//        }
//        set(value) {
//            this._details = value
//            this._updateDate = LocalDateTime.now()
//        }
//    var _creationDate = this.creationDate
//    private set
//
//    var _updateDate = this.updateDate.toString()
}
