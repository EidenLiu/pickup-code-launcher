package cn.pickup.launcher;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public final class MainActivity extends Activity {
    static final String ACTION_OPEN = "cn.pickup.launcher.OPEN";
    static final String EXTRA_DESTINATION = "destination";

    private static final int PAGE_BACKGROUND = Color.rgb(247, 248, 244);
    private static final int TEXT_PRIMARY = Color.rgb(27, 29, 27);
    private static final int TEXT_SECONDARY = Color.rgb(91, 96, 91);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Destination destination = destinationFromIntent();
        if (destination != null) {
            DeepLinkLauncher.open(this, destination);
            finish();
            return;
        }

        getWindow().setStatusBarColor(PAGE_BACKGROUND);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(buildContent());
    }

    private Destination destinationFromIntent() {
        String action = getIntent().getAction();
        if (ACTION_OPEN.equals(action)) {
            return Destination.fromKey(getIntent().getStringExtra(EXTRA_DESTINATION));
        }
        if ("cn.pickup.launcher.OPEN_CAINIAO".equals(action)) {
            return Destination.CAINIAO;
        }
        if ("cn.pickup.launcher.OPEN_TAOBAO".equals(action)) {
            return Destination.TAOBAO;
        }
        if ("cn.pickup.launcher.OPEN_PINDUODUO".equals(action)) {
            return Destination.PINDUODUO;
        }
        if ("cn.pickup.launcher.OPEN_TAOBAO_PENDING".equals(action)) {
            return Destination.TAOBAO_PENDING;
        }
        if ("cn.pickup.launcher.OPEN_PINDUODUO_PENDING".equals(action)) {
            return Destination.PINDUODUO_PENDING;
        }
        if ("cn.pickup.launcher.OPEN_JD".equals(action)) {
            return Destination.JD;
        }
        if ("cn.pickup.launcher.OPEN_XHS".equals(action)) {
            return Destination.XHS;
        }
        return null;
    }

    private View buildContent() {
        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(PAGE_BACKGROUND);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(22), dp(28), dp(22), dp(28));
        scrollView.addView(root, new ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.WRAP_CONTENT
        ));

        TextView title = text("快递取件2.0", 28, TEXT_PRIMARY, Typeface.BOLD);
        LinearLayout.LayoutParams titleParams = verticalParams(0);
        title.setLayoutParams(titleParams);
        root.addView(title);

        TextView author = text("作者：EyanLiu", 13, TEXT_SECONDARY, Typeface.NORMAL);
        LinearLayout.LayoutParams authorParams = verticalParams(dp(7));
        author.setLayoutParams(authorParams);
        root.addView(author);

        TextView description = text("快速打开取件码，也能查看各平台的待取快递。", 15, TEXT_SECONDARY, Typeface.NORMAL);
        description.setLineSpacing(0, 1.25f);
        LinearLayout.LayoutParams descriptionParams = verticalParams(dp(14));
        descriptionParams.bottomMargin = dp(26);
        description.setLayoutParams(descriptionParams);
        root.addView(description);

        root.addView(serviceRow(
                Destination.CAINIAO,
                "菜鸟驿站取件",
                "优先打开身份码，失败时进入菜鸟 App",
                Color.rgb(231, 244, 235),
                Color.rgb(20, 120, 72)
        ));
        root.addView(serviceRow(
                Destination.TAOBAO,
                "淘宝取件",
                "打开淘宝末端取件身份码",
                Color.rgb(255, 240, 227),
                Color.rgb(194, 75, 18)
        ));
        root.addView(serviceRow(
                Destination.PINDUODUO,
                "拼多多取件",
                "打开多多买菜取件身份码",
                Color.rgb(255, 232, 236),
                Color.rgb(190, 35, 60)
        ));
        TextView pendingTitle = text("查看待取", 17, TEXT_PRIMARY, Typeface.BOLD);
        LinearLayout.LayoutParams pendingTitleParams = verticalParams(dp(8));
        pendingTitleParams.bottomMargin = dp(10);
        pendingTitle.setLayoutParams(pendingTitleParams);
        root.addView(pendingTitle);

        TextView pendingDescription = text(
                "先确认有没有包裹，再前往驿站取件。",
                14,
                TEXT_SECONDARY,
                Typeface.NORMAL
        );
        LinearLayout.LayoutParams pendingDescriptionParams = verticalParams(0);
        pendingDescriptionParams.bottomMargin = dp(12);
        pendingDescription.setLayoutParams(pendingDescriptionParams);
        root.addView(pendingDescription);

        root.addView(serviceRow(
                Destination.TAOBAO_PENDING,
                "淘宝待取快递",
                "打开淘宝末端驿站待取列表",
                Color.rgb(255, 240, 227),
                Color.rgb(194, 75, 18)
        ));
        root.addView(serviceRow(
                Destination.PINDUODUO_PENDING,
                "拼多多待取快递",
                "打开拼多多包裹待取列表",
                Color.rgb(255, 232, 236),
                Color.rgb(190, 35, 60)
        ));
        root.addView(serviceRow(
                Destination.JD,
                "京东待取快递",
                "打开京东订单列表",
                Color.rgb(255, 239, 214),
                Color.rgb(180, 93, 13)
        ));
        root.addView(serviceRow(
                Destination.XHS,
                "小红书待取快递",
                "打开小红书订单列表",
                Color.rgb(255, 232, 238),
                Color.rgb(204, 63, 103)
        ));

        TextView shortcutTitle = text("添加到桌面", 17, TEXT_PRIMARY, Typeface.BOLD);
        LinearLayout.LayoutParams shortcutTitleParams = verticalParams(dp(18));
        shortcutTitleParams.bottomMargin = dp(10);
        shortcutTitle.setLayoutParams(shortcutTitleParams);
        root.addView(shortcutTitle);

        TextView shortcutDescription = text(
                "添加后不必进入本应用，桌面点击一次即可跳转。",
                14,
                TEXT_SECONDARY,
                Typeface.NORMAL
        );
        LinearLayout.LayoutParams shortcutDescriptionParams = verticalParams(0);
        shortcutDescriptionParams.bottomMargin = dp(12);
        shortcutDescription.setLayoutParams(shortcutDescriptionParams);
        root.addView(shortcutDescription);

        LinearLayout shortcutButtons = new LinearLayout(this);
        shortcutButtons.setOrientation(LinearLayout.HORIZONTAL);
        shortcutButtons.setWeightSum(3f);
        addPinButton(shortcutButtons, Destination.CAINIAO, "菜鸟码");
        addPinButton(shortcutButtons, Destination.TAOBAO, "淘宝码");
        addPinButton(shortcutButtons, Destination.PINDUODUO, "拼多多码");
        root.addView(shortcutButtons);

        LinearLayout pendingShortcutButtons = new LinearLayout(this);
        pendingShortcutButtons.setOrientation(LinearLayout.HORIZONTAL);
        pendingShortcutButtons.setWeightSum(4f);
        LinearLayout.LayoutParams pendingShortcutParams = verticalParams(dp(8));
        pendingShortcutButtons.setLayoutParams(pendingShortcutParams);
        addPinButton(pendingShortcutButtons, Destination.TAOBAO_PENDING, "淘宝待取");
        addPinButton(pendingShortcutButtons, Destination.PINDUODUO_PENDING, "拼多多待取");
        addPinButton(pendingShortcutButtons, Destination.JD, "京东待取");
        addPinButton(pendingShortcutButtons, Destination.XHS, "小红书待取");
        root.addView(pendingShortcutButtons);

        return scrollView;
    }

    private View serviceRow(
            Destination destination,
            String title,
            String subtitle,
            int backgroundColor,
            int accentColor
    ) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(dp(16), dp(15), dp(14), dp(15));
        row.setMinimumHeight(dp(82));
        row.setBackground(rounded(backgroundColor, 8));
        row.setClickable(true);
        row.setFocusable(true);
        row.setContentDescription("打开" + destination.title);
        row.setOnClickListener(view -> DeepLinkLauncher.open(this, destination));

        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        rowParams.bottomMargin = dp(12);
        row.setLayoutParams(rowParams);

        TextView mark = text(destination.mark, 18, Color.WHITE, Typeface.BOLD);
        mark.setGravity(Gravity.CENTER);
        mark.setBackground(rounded(accentColor, 7));
        row.addView(mark, new LinearLayout.LayoutParams(dp(40), dp(40)));

        LinearLayout labels = new LinearLayout(this);
        labels.setOrientation(LinearLayout.VERTICAL);
        labels.setPadding(dp(14), 0, dp(10), 0);

        TextView titleView = text(title, 17, TEXT_PRIMARY, Typeface.BOLD);
        labels.addView(titleView);

        TextView subtitleView = text(subtitle, 13, TEXT_SECONDARY, Typeface.NORMAL);
        LinearLayout.LayoutParams subtitleParams = verticalParams(dp(3));
        subtitleView.setLayoutParams(subtitleParams);
        labels.addView(subtitleView);

        row.addView(labels, new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
        ));

        TextView arrow = text("›", 28, accentColor, Typeface.NORMAL);
        arrow.setGravity(Gravity.CENTER);
        row.addView(arrow, new LinearLayout.LayoutParams(dp(28), dp(44)));

        return row;
    }

    private void addPinButton(
            LinearLayout parent,
            Destination destination,
            String label
    ) {
        TextView button = text(label, 14, TEXT_PRIMARY, Typeface.BOLD);
        button.setGravity(Gravity.CENTER);
        button.setBackground(rounded(Color.WHITE, 8));
        button.setClickable(true);
        button.setFocusable(true);
        button.setContentDescription("将" + destination.title + "添加到桌面");
        button.setOnClickListener(view -> ShortcutPinning.request(this, destination));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, dp(48), 1f);
        if (parent.getChildCount() > 0) {
            params.leftMargin = dp(8);
        }
        parent.addView(button, params);
    }

    private TextView text(String value, float sizeSp, int color, int style) {
        TextView view = new TextView(this);
        view.setText(value);
        view.setTextSize(sizeSp);
        view.setTextColor(color);
        view.setTypeface(Typeface.create("sans", style));
        view.setIncludeFontPadding(false);
        return view;
    }

    private LinearLayout.LayoutParams verticalParams(int topMargin) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.topMargin = topMargin;
        return params;
    }

    private GradientDrawable rounded(int color, int radiusDp) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(dp(radiusDp));
        return drawable;
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
