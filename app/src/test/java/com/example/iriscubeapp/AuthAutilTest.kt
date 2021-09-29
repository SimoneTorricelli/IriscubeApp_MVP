package com.example.iriscubeapp

import com.example.iriscubeapp.presenter.NetworkTestPresenter
import com.example.iriscubeapp.signTest.AuthUtil
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class AuthUtilTest {

    @ExperimentalCoroutinesApi
    val dispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun value200() = runBlocking {
        assert(NetworkTestPresenter().getMovement())
    }

    @Test
    fun `signUp function returns false when username or password is empty`() {
        val userName = ""
        val password = ""
        val repeatPassword = ""
        assert(!AuthUtil.signUp(userName, password, repeatPassword))

    }

    @Test
    fun `signUp function returns false when username is taken`() {
        val userName = "Peter"
        val password = "12345"
        val repeatPassword = "12345"
        assert(!AuthUtil.signUp(userName, password, repeatPassword))
    }

    @Test
    fun `signUp function returns false when password and repeat password don't match`() {
        val userName = "James"
        val password = "12345"
        val repeatPassword = "67890"
        assert(!AuthUtil.signUp(userName, password, repeatPassword))
    }

    @Test
    fun `signUp function returns false when password is less than two characters`() {
        val userName = "Brian"
        val password = "1"
        val repeatPassword = "1"
        assert(!AuthUtil.signUp(userName, password, repeatPassword))
    }
}