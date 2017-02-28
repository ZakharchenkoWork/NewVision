package com.znshadows.newvision.mvp.models;

import com.google.gson.Gson;
import com.znshadows.newvision.BaseTest;
import com.znshadows.newvision.TestApplication;
import com.znshadows.newvision.utils.TestUtils;
import com.znshadows.newvision.models.Item;
import com.znshadows.newvision.models.ReposData;
import com.znshadows.newvision.utils.TestConst;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by kostya on 26.02.2017.
 */
public class ModelImplTest extends BaseTest {

    @Inject
    GitHubApi gitHubApi;

    Model model;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        TestApplication.getTestAppComponent().inject(this);
        model = new ModelImpl();
    }

    @Test
    public void testGetRepoList() {

        ReposData repos = new Gson().fromJson(TestUtils.REPOS_JSON, ReposData.class);
        when(gitHubApi.getData(TestConst.TEST_REPO)).thenReturn(Observable.just(repos));


        TestObserver<ReposData> testSubscriber = Single.just(repos).test();
        model.getRepoList(TestConst.TEST_REPO).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        List<Item> actual = ((ReposData)testSubscriber.getEvents().get(0).get(0)).getItems();

        assertEquals(30, actual.size());
        assertEquals("Android-Rate", actual.get(0).getName());
        assertEquals("andrey7mel/Android-Rate", actual.get(0).getFullName());
        assertEquals(26314692, actual.get(0).getId());
    }

    /*@Test
    public void getRepoListTest() throws Exception {

        ReposData repos = new Gson().fromJson(TestUtils.REPOS_JSON, ReposData.class);

        when(gitHubApi.getData(TestConst.TEST_REPO)).thenReturn(Observable.just(repos));

        TestSubscriber<List<ReposData>> testSubscriber = new TestSubscriber<>();
        model.getRepoList(TestConst.TEST_REPO).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        List<ReposData> actual = testSubscriber.getOnNextEvents().get(0);

        assertEquals(3, actual.size());
        assertEquals("QuickStart", actual.get(0).getName());
        assertEquals("94870e23f1cfafe7201bf82985b61188f650b245", actual.get(0).getCommit().getSha());
    }*/
}