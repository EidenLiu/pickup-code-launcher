package cn.pickup.launcher;

import java.util.Locale;

enum Destination {
    CAINIAO(
            "cainiao",
            "菜鸟身份码",
            "菜",
            "com.cainiao.wireless",
            new String[] {
                    "cainiao://desktop/station_code"
            },
            "https://market.m.taobao.com/app/cn-yz/multi-activity/authCode.html?bizEntry=ALIPAY_GUOGUO",
            true
    ),
    TAOBAO(
            "taobao",
            "淘宝取件码",
            "淘",
            "com.taobao.taobao",
            new String[] {
                    "taobao://m.taobao.com/tbopen/index.html?h5Url=https%3A%2F%2Fpages-fast.m.taobao.com%2Fwow%2Fz%2Funiapp%2F1011717%2Flast-mile-fe%2Fend-collect-platform%2Fidentity-code%3Fx-ssr%3Dtrue",
                    "tbopen://m.taobao.com/tbopen/index.html?h5Url=https%3A%2F%2Fpages-fast.m.taobao.com%2Fwow%2Fz%2Funiapp%2F1011717%2Flast-mile-fe%2Fend-collect-platform%2Fidentity-code%3Fx-ssr%3Dtrue"
            },
            "https://pages-fast.m.taobao.com/wow/z/uniapp/1011717/last-mile-fe/end-collect-platform/identity-code?x-ssr=true"
    ),
    TAOBAO_PENDING(
            "taobao_pending",
            "淘宝待取快递",
            "淘",
            "com.taobao.taobao",
            new String[] {
                    "taobao://m.taobao.com/tbopen/index.html?h5Url=https%3A%2F%2Fpages-fast.m.taobao.com%2Fwow%2Fz%2Funiapp%2F1100333%2Flast-mile-fe%2Fm-end-school-tab%2Fhome",
                    "tbopen://m.taobao.com/tbopen/index.html?h5Url=https%3A%2F%2Fpages-fast.m.taobao.com%2Fwow%2Fz%2Funiapp%2F1100333%2Flast-mile-fe%2Fm-end-school-tab%2Fhome"
            },
            "https://pages-fast.m.taobao.com/wow/z/uniapp/1100333/last-mile-fe/m-end-school-tab/home"
    ),
    PINDUODUO(
            "pinduoduo",
            "拼多多身份码",
            "拼",
            "com.xunmeng.pinduoduo",
            new String[] {
                    "pinduoduo://com.xunmeng.pinduoduo/mdkd/package?tab=ID_CODE&entry_source=11&refer_page_name=login&refer_page_sn=10169",
                    "pinduoduo://com.xunmeng.pinduoduo/mdkd/package?tab=ID_CODE&entry_source=11",
                    "pinduoduo://com.xunmeng.pinduoduo/mdkd/package"
            },
            "https://m.pinduoduo.net/mdkd/package?entry_source=18&extra_params=from_wx_jump%3D1&p_channel=0"
    ),
    PINDUODUO_PENDING(
            "pinduoduo_pending",
            "拼多多待取快递",
            "拼",
            "com.xunmeng.pinduoduo",
            new String[] {
                    "pinduoduo://com.xunmeng.pinduoduo/mdkd/package"
            },
            "https://m.pinduoduo.net/mdkd/package?entry_source=18&extra_params=from_wx_jump%3D1&p_channel=0"
    ),
    JD(
            "jd",
            "京东待取快递",
            "京",
            "com.jingdong.app.mall",
            new String[] {
                    "openjd://virtual?params=%7B%22category%22%3A%22jump%22%2C%22des%22%3A%22orderlist%22%7D"
            },
            "https://order.jd.com/center/list.action"
    ),
    XHS(
            "xhs",
            "小红书待取快递",
            "书",
            "com.xingin.xhs",
            new String[] {
                    "xhsdiscover://rn/lancer-order/order/list"
            },
            "https://www.xiaohongshu.com/"
    );

    final String key;
    final String title;
    final String mark;
    final String packageName;
    final String[] appUris;
    final String webUri;
    final boolean openAppWhenDeepLinkUnavailable;

    Destination(
            String key,
            String title,
            String mark,
            String packageName,
            String[] appUris,
            String webUri
    ) {
        this(key, title, mark, packageName, appUris, webUri, false);
    }

    Destination(
            String key,
            String title,
            String mark,
            String packageName,
            String[] appUris,
            String webUri,
            boolean openAppWhenDeepLinkUnavailable
    ) {
        this.key = key;
        this.title = title;
        this.mark = mark;
        this.packageName = packageName;
        this.appUris = appUris;
        this.webUri = webUri;
        this.openAppWhenDeepLinkUnavailable = openAppWhenDeepLinkUnavailable;
    }

    static Destination fromKey(String value) {
        if (value == null) {
            return null;
        }
        String normalized = value.toLowerCase(Locale.ROOT);
        for (Destination destination : values()) {
            if (destination.key.equals(normalized)) {
                return destination;
            }
        }
        return null;
    }
}
