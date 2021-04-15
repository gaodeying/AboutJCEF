import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.OS;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefRequestCallback;
import org.cef.handler.CefDownloadHandlerAdapter;
import org.cef.handler.CefLifeSpanHandlerAdapter;
import org.cef.handler.CefLoadHandler;
import org.cef.handler.CefLoadHandlerAdapter;
import org.cef.handler.CefRequestHandlerAdapter;
import org.cef.misc.BoolRef;
import org.cef.misc.StringRef;
import org.cef.network.CefRequest;
import org.cef.network.CefResponse;
import org.cef.network.CefURLRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class main {
    public static void main(String[] args) {
        String url = "http://rms.sjst.test.meituan.com/web/fe.rms-report/pos.html?appCode=43&bizlogintoken=VXkpGCLCl8aYv7VWrwDXi1HWOZvn63D9dfvz6jqjxX0v9LbNW2xoshYz0M7Dx9Ps6VYFGr5F_yJdtDtPSbvPBw&merchantNo=51795085&poiid=600015048&_saas_monitor_traceId=51795085_c480a680ea668267bd84af025e337731&_fe_page_start_time=1617957246676#businessTotal";
//        new crashDemo();
//        new JCEFDemo(url, false, true);

        CefSettings DEFAULT_SETTINGS = new CefSettings();
        DEFAULT_SETTINGS.windowless_rendering_enabled = false;

        CefApp cefApp = CefApp.getInstance(DEFAULT_SETTINGS);
        CefClient client = cefApp.createClient();
        CefBrowser browser = client.createBrowser(url, false, false);

        client.addLoadHandler(new CefLoadHandlerAdapter() {
            @Override
            public void onLoadingStateChange(CefBrowser browser, boolean isLoading, boolean canGoBack, boolean canGoForward) {
                super.onLoadingStateChange(browser, isLoading, canGoBack, canGoForward);
            }

            @Override
            public void onLoadError(CefBrowser browser, CefFrame frame, ErrorCode errorCode, String errorText, String failedUrl) {
                super.onLoadError(browser, frame, errorCode, errorText, failedUrl);
            }

            @Override
            public void onLoadStart(CefBrowser browser, CefFrame frame, CefRequest.TransitionType transitionType) {
                super.onLoadStart(browser, frame, transitionType);
                // 2 start load
            }

            @Override
            public void onLoadEnd(CefBrowser browser, CefFrame frame, int httpStatusCode) {
                super.onLoadEnd(browser, frame, httpStatusCode);
                // 4
            }
        });

        client.addLifeSpanHandler(new CefLifeSpanHandlerAdapter() {
            @Override
            public boolean onBeforePopup(CefBrowser browser, CefFrame frame, String target_url, String target_frame_name) {
                return super.onBeforePopup(browser, frame, target_url, target_frame_name);
            }

            @Override
            public void onAfterCreated(CefBrowser browser) {super.onAfterCreated(browser);
                // 1 链路起点
            }

            @Override
            public void onAfterParentChanged(CefBrowser browser) {
                super.onAfterParentChanged(browser);
            }

            @Override
            public void onBeforeClose(CefBrowser browser) {
                super.onBeforeClose(browser);
            }
        });

        client.addRequestHandler(new CefRequestHandlerAdapter() {
            @Override
            public boolean onBeforeBrowse(CefBrowser browser, CefFrame frame, CefRequest request, boolean user_gesture, boolean is_redirect) {
                String url = request.getURL();
                url = url + "123";
                request.setURL(url);
                return super.onBeforeBrowse(browser, frame, request, user_gesture, is_redirect);

                // 1
            }

            @Override
            public boolean onBeforeResourceLoad(CefBrowser browser, CefFrame frame, CefRequest request) {
                return super.onBeforeResourceLoad(browser, frame, request);
            }

            @Override
            public boolean onCertificateError(CefBrowser browser, CefLoadHandler.ErrorCode cert_error, String request_url, CefRequestCallback callback) {
                return super.onCertificateError(browser, cert_error, request_url, callback);
            }

            @Override
            public boolean onQuotaRequest(CefBrowser browser, String origin_url, long new_size, CefRequestCallback callback) {
                return super.onQuotaRequest(browser, origin_url, new_size, callback);
            }

            @Override
            public boolean onResourceResponse(CefBrowser browser, CefFrame frame, CefRequest request, CefResponse response) {
                return super.onResourceResponse(browser, frame, request, response);
            }

            @Override
            public void onPluginCrashed(CefBrowser browser, String pluginPath) {
                super.onPluginCrashed(browser, pluginPath);
            }

            @Override
            public void onProtocolExecution(CefBrowser browser, String url, BoolRef allow_os_execution) {
                super.onProtocolExecution(browser, url, allow_os_execution);
            }

            @Override
            public void onRenderProcessTerminated(CefBrowser browser, TerminationStatus status) {
                super.onRenderProcessTerminated(browser, status);
            }

            @Override
            public void onResourceLoadComplete(CefBrowser browser, CefFrame frame, CefRequest request, CefResponse response, CefURLRequest.Status status, long receivedContentLength) {
                super.onResourceLoadComplete(browser, frame, request, response, status, receivedContentLength);
            }

            @Override
            public void onResourceRedirect(CefBrowser browser, CefFrame frame, CefRequest request, CefResponse response, StringRef new_url) {
                super.onResourceRedirect(browser, frame, request, response, new_url);
            }
        });

        Component browserUI = browser.getUIComponent();
        JFrame mainFrame = new JFrame();
        mainFrame.getContentPane().add(browserUI, BorderLayout.CENTER);
        mainFrame.setSize(1000, 600);
        mainFrame.setVisible(true);
        browser.setFocus(true);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                mainFrame.dispose();
                cefApp.dispose();
            }
        });

    }
}
