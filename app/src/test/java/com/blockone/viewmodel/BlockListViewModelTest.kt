package com.blockone.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.blockone.data.Block
import com.blockone.data.EachBlockInfo
import com.blockone.repository.ApiRepository
import com.blockone.rule.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class BlockListViewModelTest {

    lateinit var blockListViewModel: BlockListViewModel

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var repository: ApiRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        blockListViewModel = BlockListViewModel(repository)
    }


    @Test
    fun getLatestBlock() {

        coroutinesTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(repository.getLatestBlockRepo()).thenReturn(Block("1234"))
        }

        blockListViewModel.getLatestBlock()

        Assert.assertEquals(
            "1234",
            blockListViewModel.getLiveDataBlock().value?.last_irreversible_block_id
        )
    }


    @Test
    fun getTotalBlock() {

        var blockInfo =
            EachBlockInfo("RajeshPalepu", "12", "XYZ", "2:30", "ABC", ArrayList())

        coroutinesTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(repository.getBlockRepo("123")).thenReturn(blockInfo)
        }
        blockListViewModel.getLatestBlockList("123")

        Assert.assertEquals(
            "XYZ",
            blockListViewModel.getLiveDataEachBlock().value?.id
        )
    }


}