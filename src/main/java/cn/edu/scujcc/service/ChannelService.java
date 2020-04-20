package cn.edu.scujcc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.dao.ChannelRepository;
import cn.edu.scujcc.model.Channel;

/**
 * 提供频道相关业务逻辑
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
			c.setTitle("锦城电视"+(i+1)+"台");
			c.setQuality("1080P高清");
			c.setUrl("http://test.com");
			channels.add(c);
		}
	}
	
	/**
	 * 获取一个频道
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
	 * 获取所有频道
	 * @return
	 */
	public List<Channel> getAllChannel() {
		return this.channels;
	}
	
	/**
	 * 删除指定频道
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
	 * 更新一个频道
	 * @param c 待更新的频道
	 * @return 更新后的频道
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
	 * 新建频道
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
	 * 搜索方法
	 * @param title
	 * @param quality
	 * @return
	 */
	public List<Channel> search(String title,String quality) {
		return repo.findByTitleAndQuality(title, quality);
	}
}

