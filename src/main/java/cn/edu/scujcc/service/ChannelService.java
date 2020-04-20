package cn.edu.scujcc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.dao.ChannelRepository;
import cn.edu.scujcc.model.Channel;

/**
 * �ṩƵ�����ҵ���߼�
 * @author ZYF
 *
 */
@Service
public class ChannelService {
	@Autowired
	private ChannelRepository repo;
	private List<Channel> channels;

	public ChannelService() {
		channels = new ArrayList<>();
		for (int i=0; i< 10; i++) {
			Channel c = new Channel();
			c.setId(i + 1);
			c.setTitle("���ǵ���"+(i+1)+"̨");
			c.setQuality("1080P����");
			c.setUrl("http://test.com");
			channels.add(c);
		}
	}
	
	/**
	 * ��ȡһ��Ƶ��
	 * @param id
	 * @return
	 */
	public Channel getChannel(int id) {
		Channel result = null;
		for(Channel c: this.channels) {
			if(id==c.getId()) {
				result = c;
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * ��ȡ����Ƶ��
	 * @return
	 */
	public List<Channel> getAllChannel() {
		return this.channels;
	}
	
	/**
	 * ɾ��ָ��Ƶ��
	 * @param id
	 * @return
	 */
	public boolean deleteChannel(int id) {
		boolean result = false;
		for (Channel c : this.channels) {
			if (id == c.getId()) {
				this.channels.remove(c);
				result = true;
				break;
			}
		}
		return result;
	}
	
	/**
	 * ����һ��Ƶ��
	 * @param c �����µ�Ƶ��
	 * @return ���º��Ƶ��
	 */
	public Channel updateChannel(Channel c) {
		Channel toChange = getChannel(c.getId());
		if(toChange != null) {
			toChange.setTitle(c.getTitle());
			toChange.setQuality(c.getQuality());
			toChange.setUrl(c.getUrl());
		}
		return toChange;
	}
	
	/**
	 * �½�Ƶ��
	 * @param c
	 * @return
	 */
	public Channel createChannel(Channel c) {
		/*c.setId(this.channels.get(this.channels.size() - 1).getId() + 1);
		this.channels.add(c);
		return c;*/
		return repo.save(c);
	}
	
	/**
	 * ��������
	 * @param title
	 * @param quality
	 * @return
	 */
	public List<Channel> search(String title,String quality) {
		return repo.findByTitleAndQuality(title, quality);
	}
}
