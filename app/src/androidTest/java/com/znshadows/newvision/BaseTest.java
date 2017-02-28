package com.znshadows.newvision;

import com.znshadows.newvision.di.components.TestComponent;
import com.znshadows.newvision.utils.TestUtils;

import org.junit.Before;
import org.junit.Ignore;


/**
 * Created by kostya on 26.02.2017.
 */
/*@RunWith(RobolectricGradleTestRunner.class)
@Config(application = TestApplication.class,
        constants = BuildConfig.class,
        sdk = 21)*/
@Ignore
public class BaseTest {

    public TestComponent component;
    public TestUtils testUtils;

    @Before
    public void setUp() throws Exception {
        component = (TestComponent) TestApplication.getTestAppComponent();
        testUtils = new TestUtils();
    }

}