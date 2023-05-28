package com.anusha.coffee.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.anusha.coffee.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SearchActivity extends AppCompatActivity {

    private EditText mSearchEditText;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchEditText = findViewById(R.id.edit_text_search);
        mWebView = findViewById(R.id.web_view);

        // Set up the WebView settings
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set up the search button click listener
        Button searchButton = findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = mSearchEditText.getText().toString();

                // Load the Google Search URL with the user's query as a parameter
                String url = "https://www.google.com/search?q=" + query;
                mWebView.loadUrl(url);

                // Set up the WebView client to intercept the loaded webpage and extract relevant information
                // Set up the WebView client to intercept the loaded webpage and extract relevant information
                mWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);

                        // Use JavaScript to get the page source
                        view.evaluateJavascript(
                                "(function() { return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'); })();",
                                new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html) {
                                        // Use Jsoup to parse the HTML content of the loaded webpage
                                        Document doc = Jsoup.parse(html);

                                        // Extract the relevant information from the parsed HTML content
                                        Element searchResult = doc.selectFirst("div#search div.g");
                                        if (searchResult != null) {
                                            String title = searchResult.selectFirst("h3").text();
                                            String url1 = searchResult.selectFirst("a[href]").attr("href");

                                            // Display the extracted information in a TextView or another UI element
                                            // For example:
                                            TextView resultTextView = findViewById(R.id.text_view_result);
                                            resultTextView.setText(title + "\n" + url);
                                        }
                                    }
                                });
                    }
                });

            }
        });
    }
}
