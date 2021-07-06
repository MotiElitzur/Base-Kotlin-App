package base.kotlin.model

import android.content.Context

import androidx.lifecycle.MutableLiveData

/**
 * This class handles the users list.
 */
class UsersRepository {

    // region Data Members

    // The Users list.
    private val _usersList: MutableList<User> = mutableListOf()

    // The user live data list.
    var usersLiveData = MutableLiveData<MutableList<User>>()

    // endregion

    // region Public Methods

    /**
     * This function start to read and add the users from the user source.
     *
     * @param context Context to get the users from the source.
     */
    fun getUsers(context: Context) {

        // Run on another thread in order to not interrupt the ui.
        Thread {

            // Add the users list to the code.
            val users : MutableList<User> = mutableListOf()

            // Set an example users.
            for (i in 1 until 101){
                users.add(User(i.toString(), "User", "1234"))
            }

            addUsersList(users)

        }.start()
    }

    // endregion

    // region Private Methods

    /**
     * Receive a users list and add it to our user list.
     *
     * @param users The users list to add.
     */
    private fun addUsersList(users: MutableList<User>?) {

        // Add the new users list to our user list.
        _usersList.addAll(users!!)

        // Update the users live list.
        fetchLiveList()
    }


    /**
     * Receive a user and add it to our user list.
     *
     * @param user The user to add.
     */
    private fun addUser(user: User) {

        // Add the new user to our user list.
        _usersList.add(user)

        // Update the users live list.
        fetchLiveList()
    }

    /**
     * Update the users live list with our users list.
     */
    private fun fetchLiveList() {

        // Update the users live list with our users list.
        usersLiveData.postValue(_usersList)
    }

    // endregion
}