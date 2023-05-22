package lt.vgrabauskas.homework07

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
@Parcelize
data class Note(
    private val _id: Int,
    private var _name: String,
    private var _details: String,
    private var _creationDate: LocalDateTime = LocalDateTime.now(),
    private var _updateDate: LocalDateTime = LocalDateTime.now(),
): Parcelable {
    @IgnoredOnParcel
    var id = this._id
        private set

    @IgnoredOnParcel
    var name: String = ""
        get():String {
            field = this._name
            return field
        }
        set(value) {
            field = value
            this._name = value
            this._updateDate = LocalDateTime.now()
        }

    @IgnoredOnParcel
    var details: String
        get():String {
            return _details
        }
        set(value) {
            this._details = value
            this._updateDate = LocalDateTime.now()
        }

    @IgnoredOnParcel
    var creationDate = this._creationDate
        private set

    @IgnoredOnParcel
    var updateDate: LocalDateTime
        get() = this._updateDate
        private set(value) {
            this._updateDate = value
        }


}
