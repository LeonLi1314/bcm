package operamasks.ui.model;

import java.util.ArrayList;
import java.util.List;

import com.rtmap.traffic.bcm.domain.SysMenu;

import lqs.frame.util.StringUtils;

public class OmMenu {
	public static List<OmMenuModel> convertToOmMenuModel(List<SysMenu> menus) {
		List<OmMenuModel> rst = new ArrayList<>();
		
		for (SysMenu sysMenu : menus) {
			OmMenuModel model = new OmMenuModel();
			model.setId(sysMenu.getMenuNo());
			model.setText(sysMenu.getMenuName());
			
			if(!StringUtils.isNullOrEmpty(sysMenu.getMenuUrl())){
				model.setUrl(sysMenu.getMenuUrl());
			}
			
			if(!StringUtils.isNullOrEmpty(sysMenu.getParentMenuNo())){
				model.setPid(sysMenu.getParentMenuNo());
			}else{
				model.setExpanded(true);
			}
			
			rst.add(model);
		}
		
		return rst;
	}
	
	public static String convertToOmMenuJson(List<SysMenu> menus) {
		StringBuilder sb = new StringBuilder(256);

		for (SysMenu menu : menus) {
			if (sb.length() > 0) {
				sb.append(",");
			}

			sb.append(tojson(menu));
		}

		sb.insert(0, "[");
		sb.append("]");

		return sb.toString();
	}

	private static Object tojson(SysMenu menu) {
		StringBuilder sb = new StringBuilder(128);
		sb.append("{\n");
		sb.append(String.format("id: \"%s\",\n", menu.getMenuNo()));

		//如果父节点不为空，则为叶子节点，增加父节点路径
		if (menu.getParentMenuNo() != null &&menu.getParentMenuNo().length() > 0) {
			sb.append(String.format("pid: \"%s\",\n", menu.getParentMenuNo()));
		}

		sb.append(String.format("text: \"%s\",\n", menu.getMenuName()));

		if (menu.getMenuUrl() == null || menu.getMenuUrl().length() == 0) {
			sb.append("expanded: true\n");
		} else {
			sb.append(String.format("url: WEB_ROOT+\"%s\"\n", menu.getMenuUrl()));
		}

		sb.append("}");

		return sb.toString();
	}
}
