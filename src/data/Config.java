package data;

import util.Const;
import util.FileUtil;
import util.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class Config {
    private static final String CONFIG_PATH = "./config.json";
    private static final File FILE = new File(CONFIG_PATH);
    private String userName;
    private String password;
    private List<Product> product;

    public String getPassword() {
        return password;
    }

    public Config setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Config setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public List<Product> getProductList() {
        return product;
    }

    public Config setProductList(List<Product> product) {
        this.product = product;
        return this;
    }

    /**
     * 将 productList 转换为 swing Table 的 Vector
     */
    public Vector<Product> productListToVector() {
        return new Vector<>(product);
    }

    /**
     * 初始化配置
     */
    public static void initConfig() {
        // 判断是否存在 config.json 文件
        if (!FILE.exists()) {
            // 不存在则创建
            Config config = new Config();
            // 设置默认用户名和密码
            config.setUserName("admin").setPassword("admin").setProductList(new Vector<>());
            Const.config = config;
            save();
        } else {
            try {
                String content = FileUtil.readFile(CONFIG_PATH);
                Const.config = JsonUtil.decodeFromString(content, Config.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 保存配置
     */
    public static void save() {
        String json = JsonUtil.encodeToString(Const.config);
        // 保存 json 文件
        boolean success = FileUtil.saveFile(CONFIG_PATH, json);
        if (!success) {
            throw new RuntimeException("保存配置失败");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return Objects.equals(userName, config.userName) && Objects.equals(password, config.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}

