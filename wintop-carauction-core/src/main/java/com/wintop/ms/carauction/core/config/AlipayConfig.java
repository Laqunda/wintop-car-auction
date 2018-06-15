package com.wintop.ms.carauction.core.config;

/***
 * 支付宝-支付配置类
 */
public class AlipayConfig {

    /**支付宝-支付服务地址
     * */
    public static String ALI_PAY_SERVER_URL = "https://openapi.alipay.com/gateway.do";

    /***
     * 支付应用id
     */
    public static String APP_ID = "2018031502376498";

    /***
     * 支付私钥
     */
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCA7M13KRrekBXIzD1q+/uH1/JbjkcwWftL0WHdTi4DWobn+gEBnlLq2cu801I+SSM2hr2ieB6LFRRBSgmSa3HOrFDuneN1hYh23J3rVXcliP6MAfltTFs27XUFeuSun3ayetROxEpBZqHXu1O9SvKLEwvHPWDQWso7WLykqaUPEV/v+NEUtOB7r5lDiEv2I+E+uhwsHA91ODeIJvHxVkaB2Hbsrb7b92BJmgv+dTabJ/vG4fqmZ5SHdELN5NLpYYG4gVV3x3kCnBeUCC0wRtX/eGV2kbUWgpEm94YovPB2JbMyxReiUcaUUkueL3UlaDRsintxB/6mwuet8TwDNkmVAgMBAAECggEABZS6rD7eR+Yq2z9kJT51PAMwHaCCGmi6xSRyPRdGTKTdlCsxYfWNl1cIR8tKEyItYQ3iJadXgyPNdaoT0f/OtGinND0nfI3kQ2j3eRmBB+HvSvZG6uFUTYNn/m28eoayp7VDfYlnwX8E4IbecCJPb4/Kgbx5dWuIt5r2x5YEbCt/FPl71qasLiGD664WCBvvbufZG1g9t0xrjM7iqYxObQJn7aID+ohhDMwTtXFn9gg6Y9VEokuYczR0iZnA2njMujUF4Bc2vsw2e/dIKMxf2sRy4BCcwuzRbBC5l2Lg8rEdDzvNyAKzLPRcWuZDS01hPMWo1MfvxDSQWUuZdTtg/QKBgQDtbm5o+6PFDxk3C4eKlvEfLVI7tP0AC0w4aP2CKXCqxi9QZv+j3JNwvfVIMG+UoKXLpO08CLY5ibmSb4q0zrMi9kVJfoltX+w96aVeaVFanv6m87ohzAd+5LBeX7dJcJBB/rbcJzKZqq/G0XruQl0Mr9f+YTy2YCkO003LKFCYQwKBgQCLAfxkS7vCSd+9s1rtY7aLTxoXlQxgN3eyITKwQrYOZQyYqtzK6J5bLmsO7LEinaNyc2FBraTV6Sn5LyHcB/F85aExrpvn5qN2Y8CNQixgeUNdMK7k/xTNa1J3RfTttym5AABsxEKH/frU/wBOzJqsGY898mXl0LkEz9CUyMZFRwKBgQCc3cAeplreIAqVvo1PeEhYQ9Y30mzxMYWrI1qSO5TPj17FLTeq6FmVPDuo9S9TMAw6Dn4aktrqCt9LK1yl9oaOC81dMDfVxnFLumlihgyU5cTzQsXv/F/ba3ZG89AhujflEkd4DoG02vuFTPN+JORbNHZs/4tgJwoZXGviVIFjBwKBgDOgZTCKwA0EishVmGuaIO3UTwh7HsGNWDMcCUtVO3BVLzx0GNRzWHzc3H4Kgi8tBMs49uIBr7qSgjg9vQGPIa0VS6y8AMM78XHC0nykGv8pik9OCWabJgq01Ufe/3xaDnDbA01iBqOjPElm+6JIcxl/1KdZI2cbAdw7omffuWN5AoGAIZH15maefJnkRytJDd4jqjz91i63bpqR5SrG+tNr7wEH1GXt48wlHWKlCVOZZZcTUUrt114MiMQfV/JF7BQy8g5LroWQYovcfXeR4sOEfGj24vBdYfHf2GUtbzYJzd9Oyvo/h5tZvGLXRVS4ny4lvOg2W4Ekz7C2RyueE29hXMw=";

    /***
     * 支付公钥
     */
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoDDArpPA7+iFtH331/Y4hWa873aKBQjOq1KqE/WjNcPR6wAQFaX6JzNM8AYuhnmhGDU2JdCyEF9IUBJ5HNqBXI8yXePXtSm80ykYmS3TwMVoBjT11E57tZF2+s3eqf8iYQdfXK0Yy/s2aZIKHillSaLiHSbmnpGT8nXQf42CJP7lu7UyHU+c1PFSJFXyQeC6mLhl9hOIxBo0wHWEgd9ewvS1JJs7Nnb7zFC1Ogm9ua3l5jKG7gczuudecmX9BOFAocM8qKGj++Uw+aNwRraaH8WWU0yuq5VT/dLSXhSw2c4DfkJlfW7rq3I7eDPCUwxYcV3CzGuePTolzHIBJdyG4wIDAQAB";


    /***
     * 自定义支付宝配置
     * @param appId
     * @param appPrivateKay
     * @param alipayPublicKey
     */
    public AlipayConfig(String appId,String appPrivateKay,String alipayPublicKey){
        this.APP_ID = appId;
        this.APP_PRIVATE_KEY = appPrivateKay;
        this.ALIPAY_PUBLIC_KEY = alipayPublicKey;
    }
    public AlipayConfig(){}
}
