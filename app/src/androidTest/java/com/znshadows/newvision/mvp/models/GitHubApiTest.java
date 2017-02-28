package com.znshadows.newvision.mvp.models;

import com.znshadows.newvision.BaseTest;
import com.znshadows.newvision.models.ReposData;
import com.znshadows.newvision.utils.TestConst;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;
import okhttp3.Dispatcher;
import okhttp3.HttpUrl;

import static junit.framework.Assert.assertEquals;

/**
 * Created by kostya on 26.02.2017.
 */

public class GitHubApiTest extends BaseTest {

    //private MockWebServer server;
    private GitHubApi apiInterface;

/*    @Before
    public void setUp() throws Exception {
        super.setUp();
        server = new MockWebServer();
        server.start();
        final Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

                if (request.getPath().equals("/users/" + TestConst.TEST_OWNER + "/repos")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("json/repos"));
                } else if (request.getPath().equals("/repos/" + TestConst.TEST_OWNER + "/" + TestConst.TEST_REPO + "/branches")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("json/branches"));
                } else if (request.getPath().equals("/repos/" + TestConst.TEST_OWNER + "/" + TestConst.TEST_REPO + "/contributors")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("json/contributors"));
                }
                return new MockResponse().setResponseCode(404);
            }
        };

        server.setDispatcher(dispatcher);
        HttpUrl baseUrl = server.url("/");
        apiInterface = ApiModule.getApiInterface(baseUrl.toString());
    }*/


    @Test
    public void testGetRepositories() throws Exception {

        TestObserver<ReposData> testSubscriber = new TestObserver<>();
        apiInterface.getData(TestConst.TEST_REPO).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        ReposData actual = (ReposData) testSubscriber.getEvents().get(0).get(0);

        assertEquals(7, actual.getItems().size());
        //assertEquals("Android-Rate", actual.get(0).getName());
        //assertEquals("andrey7mel/Android-Rate", actual.get(0).getFullName());
        ///assertEquals(26314692, actual.get(0).getId());
    }

    /*@After
    public void tearDown() throws Exception {
        server.shutdown();
    }*/
}