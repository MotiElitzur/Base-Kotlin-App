package base.kotlin.viewModel

import android.content.Context
import android.content.Intent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import base.kotlin.model.User
import base.kotlin.model.UsersRepository
import base.kotlin.view.UserDetailsActivity

/**
 * The users view model that hold the users list and operations when it's change .
 */
class UsersViewModel(var context: Context) : ViewModel() {

    // region Data Members

    // The user repository.
    private val _usersRepository: UsersRepository = UsersRepository()

    // The users list.
    var _usersList: MutableLiveData<MutableList<User>>  = _usersRepository.usersLiveData

    // endregion

    // region Public Methods

    /**
     * Start to get the users.
     */
    fun startReadPhoneUser() {

        // Call the users repository to start to read the users.
        _usersRepository.getUsers(context)
    }

    /**
     * This function called when a user pressed and open an activity with user details.
     *
     * @param user The user that pressed.
     */
    fun onUserPressed(user: User?) {

        // Pass the user to user details activity.
        val intent = Intent(context, UserDetailsActivity::class.java).apply {
            putExtra(User::class.java.simpleName, user)
        }

        // start user details activity.
        context.startActivity(intent)
    }

    // endregion
}