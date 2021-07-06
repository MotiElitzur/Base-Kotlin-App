package base.kotlin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import base.kotlin.R
import base.kotlin.databinding.UserDetailsBinding
import base.kotlin.model.User

/**
 * The user details activity that contains the user details layout.
 */
class UserDetailsActivity : AppCompatActivity() {

    // region Life Cycle Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the user details binding.
        val activityBinding: UserDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.user_details)

        // Get the user.
        val user: User =
            intent.getSerializableExtra(User::class.java.simpleName) as User

        // Set the user to user details binding.
        activityBinding.user = user
    }

    // endregion
}