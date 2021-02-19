package org.lf.blog.service;

import com.jcraft.jsch.*;
import org.lf.blog.bean.SftpAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Properties;

/**
 * Created by LF on 2020/09/23.
 */
@Service
@Transactional
public class SftpService {
    private Session session;
    private Channel channel;
    private ChannelSftp channelSftp;

    public void createChannel(SftpAuthority sftpAuthority) {
        try {
            JSch jSch = new JSch();
            session = jSch.getSession(sftpAuthority.getUser(), sftpAuthority.getHost(), sftpAuthority.getPort());

            if (sftpAuthority.getPassword() != null) {
                // 使用用户名密码创建SSH
                session.setPassword(sftpAuthority.getPassword());
            } else if (sftpAuthority.getPrivateKey() != null) {
                // 使用公私钥创建SSH
                jSch.addIdentity(sftpAuthority.getPrivateKey(), sftpAuthority.getPassphrase());
            }

            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");  // 主动接收ECDSA key fingerprint，不进行HostKeyChecking
            session.setConfig(properties);
            session.setTimeout(0);  // 设置超时时间为无穷大
            session.connect(); // 通过session建立连接

            channel = session.openChannel("sftp"); // 打开SFTP通道
            channel.connect();
            channelSftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            //log.error("create sftp channel failed!", e);
            e.printStackTrace();
        }
    }

    public void closeChannel() {
        if (channel != null) {
            channel.disconnect();
        }

        if (session != null) {
            session.disconnect();
        }
    }

    public boolean uploadFile(SftpAuthority sftpAuthority, String src, String dst) {
        if (channelSftp == null) {
            //log.warn("need create channelSftp before upload file");
            return false;
        }

        if (channelSftp.isClosed()) {
            createChannel(sftpAuthority); // 如果被关闭则应重新创建
        }

        try {
            channelSftp.put(src, dst, ChannelSftp.OVERWRITE);
            //log.info("sftp upload file success! src: {}, dst: {}", src, dst);
            return true;
        } catch (SftpException e) {
            //log.error("sftp upload file failed! src: {}, dst: {}", src, dst, e);
            return false;
        }
    }

    public boolean removeFile(SftpAuthority sftpAuthority, String dst) {
        if (channelSftp == null) {
            //log.warn("need create channelSftp before remove file");
            return false;
        }

        if (channelSftp.isClosed()) {
            createChannel(sftpAuthority); // 如果被关闭则应重新创建
        }

        try {
            channelSftp.rm(dst);
            //log.info("sftp remove file success! dst: {}", dst);
            return true;
        } catch (SftpException e) {
            //log.error("sftp remove file failed! dst: {}", dst, e);
            return false;
        }
    }
}
