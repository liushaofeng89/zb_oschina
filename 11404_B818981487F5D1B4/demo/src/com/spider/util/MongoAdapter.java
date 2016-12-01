package com.spider.util;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.*;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class MongoAdapter {
    /**
     * mongodb数据库连接类
     */
    private static Logger logger = Logger.getLogger(MongoAdapter.class);
    
    private static MongoClient mongo_conn_client = null;
    private static MongoClient mongo_remote_conn_client = null;
    private static DB mongo_db_client = null;
    private static DB mongo_remote_db_client = null;
    private static Datastore mongo_ds = null;

    public MongoAdapter() {
    }

    /**
     * 获取mongodb Datastore实例
     *
     * @return Datastore
     */
    public static Datastore getDatastore() {
        if (mongo_ds == null) {
            String work = ConfigHelper.getParamValue("work");
            Morphia morphia = new Morphia();
            if (work != null && !work.equals("local")) {
                mongo_ds = morphia.createDatastore(getEndecryptMongoDB().getMongo(), Endecrypt.get3DESDecrypt(ConfigHelper.getParamValue("morphia.db.name"), work));
            } else {
                mongo_ds = morphia.createDatastore(getMongoDB().getMongo(), ConfigHelper.getParamValue("morphia.db.name"));
            }
        }

        return mongo_ds;
    }

    /**
     * 获取到Mongodb库链接
     *
     * @return DB
     */
    public static DB getMongoDB() {
        try {
            if (mongo_conn_client == null) {
                MongoCredential credential = MongoCredential.createCredential(
                        ConfigHelper.getParamValue("morphia.db.username"),
                        ConfigHelper.getParamValue("morphia.db.name"),
                        ConfigHelper.getParamValue("morphia.db.password").toCharArray()
                );
                mongo_conn_client = new MongoClient(
                        new ServerAddress(ConfigHelper.getParamValue("morphia.db.host"),
                                Integer.parseInt(ConfigHelper.getParamValue("morphia.db.port"))),
                        Arrays.asList(credential)
                );

                mongo_db_client = mongo_conn_client.getDB(ConfigHelper.getParamValue("morphia.db.name"));
                if (!mongo_db_client.isAuthenticated()) {
                    logger.info("auth fail!");
                    return null;
                }
            } else {
                if (mongo_db_client == null) {
                    mongo_db_client = mongo_conn_client.getDB(ConfigHelper.getParamValue("morphia.db.name"));
                    if (!mongo_db_client.isAuthenticated()) {
                        logger.info("auth fail!");
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mongo_db_client.setWriteConcern(WriteConcern.SAFE);

        return mongo_db_client;
    }


    /**
     * 获取到加密Mongodb库链接
     *
     * @return DB
     */
    public static DB getEndecryptMongoDB() {
        try {
            String work = ConfigHelper.getParamValue("work");
            if (mongo_conn_client == null) {
                MongoCredential credential = MongoCredential.createCredential(
                        Endecrypt.get3DESDecrypt(ConfigHelper.getParamValue("morphia.db.username"), work),
                        Endecrypt.get3DESDecrypt(ConfigHelper.getParamValue("morphia.db.name"), work),
                        Endecrypt.get3DESDecrypt(ConfigHelper.getParamValue("morphia.db.password"), work).toCharArray()
                );
                mongo_conn_client = new MongoClient(
                        new ServerAddress(
                                Endecrypt.get3DESDecrypt(ConfigHelper.getParamValue("morphia.db.host"), work),
                                Integer.parseInt(Endecrypt.get3DESDecrypt(ConfigHelper.getParamValue("morphia.db.port"), work))),
                        Arrays.asList(credential)
                );

                mongo_db_client = mongo_conn_client.getDB(Endecrypt.get3DESDecrypt(ConfigHelper.getParamValue("morphia.db.name"), work));
                if (!mongo_db_client.isAuthenticated()) {
                    logger.info("auth fail!");
                    return null;
                }
            } else {
                if (mongo_db_client == null) {
                    mongo_db_client = mongo_conn_client.getDB(Endecrypt.get3DESDecrypt(ConfigHelper.getParamValue("morphia.db.name"), work));
                    if (!mongo_db_client.isAuthenticated()) {
                        logger.info("auth fail!");
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mongo_db_client.setWriteConcern(WriteConcern.SAFE);

        return mongo_db_client;
    }

    /**
     * 获取远程Mongodb数据库链接
     *
     * @return DB
     */
    public static DB getRemoteMongoDB() {
        try {
            if (mongo_remote_conn_client == null) {
                MongoCredential credential = MongoCredential.createCredential(
                        ConfigHelper.getParamValue("morphia.db.remote.username"),
                        ConfigHelper.getParamValue("morphia.db.remote.name"),
                        ConfigHelper.getParamValue("morphia.db.remote.password").toCharArray()
                );
                mongo_remote_conn_client = new MongoClient(
                        new ServerAddress(ConfigHelper.getParamValue("morphia.db.remote.host"),
                                Integer.parseInt(ConfigHelper.getParamValue("morphia.db.remote.port"))),
                        Arrays.asList(credential)
                );

                mongo_remote_db_client = mongo_remote_conn_client.getDB(ConfigHelper.getParamValue("morphia.db.remote.name"));
                if (!mongo_remote_db_client.isAuthenticated()) {
                    logger.info("auth fail!");
                    return null;
                }
            } else {
                if (mongo_remote_db_client == null) {
                    mongo_remote_db_client = mongo_remote_conn_client.getDB(ConfigHelper.getParamValue("morphia.db.remote.name"));
                    if (!mongo_remote_db_client.isAuthenticated()) {
                        logger.info("auth fail!");
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mongo_remote_db_client.setWriteConcern(WriteConcern.SAFE);

        return mongo_remote_db_client;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
    }

}
