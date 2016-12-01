package xmg.test.testwechat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import xmg.test.testwechat.utils.WxShareAndLoginUtils;

public class MainActivity extends AppCompatActivity {

    private Button btn,btn_text,btn_image,btn_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn_text = (Button) findViewById(R.id.btn_text);
        btn_image = (Button) findViewById(R.id.btn_image);
        btn_url = (Button) findViewById(R.id.btn_url);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WxShareAndLoginUtils.WxLogin();
            }
        });
        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WxShareAndLoginUtils.WxTextShare("测试微信分享",WxShareAndLoginUtils.WECHAT_MOMENT);
            }
        });
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                WxShareAndLoginUtils.WxBitmapShare(bitmap,WxShareAndLoginUtils.WECHAT_MOMENT);
            }
        });
        btn_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
                WxShareAndLoginUtils.WxUrlShare("http://www.baidu.com", "百度", "百度一下", bitmap, WxShareAndLoginUtils.WECHAT_FRIEND);
            }
        });
    }
}
