package sample.contrast;

import tool.CommonTool;

//文本单位对比
public class Item {
	private String left;

	private String right;
	private int[][] status;

	public int[] l_path;
	public int[] r_path;
	
	public Item(String l, String r) {
		this.left = l;
		this.right = r;
		status = new int[r.length()][l.length()];
	}
	//重新初始化
	public void init(String l, String r) {
		this.left = l;
		this.right = r;
		status = new int[r.length()][l.length()];
	}
	//文本对比
	public void compare() {
		char[] l_cs = left.toCharArray();
		char[] r_cs = right.toCharArray();

		int length = r_cs.length;

		// while(count <= length)
		// {
		for (int i = 0; i < length; i++) {
			int count = 0;
			char c = r_cs[i];
			while (true) {
				int index = left.indexOf(c, count);
				if (index < 0)
					break;
				status[i][index] = 1;
				if (index > 0 && i > 0) {
					if (status[i - 1][index - 1] > 0)
						status[i][index] = status[i - 1][index - 1] + 1;
				}
				count = index + 1;
			}
		}
	}
	//显示数组集
	public void show() {
		for (int i = 0; i < status.length; i++) {
			System.out.println(CommonTool.getTools().StringUtils.link(
					status[i], ","));
		}

		System.out.println("---------------左侧的位置------------");
		System.out.println(CommonTool.getTools().StringUtils.link(l_path, ","));
		System.out.println("---------------右侧的位置------------");
		System.out.println(CommonTool.getTools().StringUtils.link(r_path, ","));
	}

	// 计算重合度
	public void builpath() {
		int length = status.length;
		l_path = new int[left.length()];
		r_path = new int[right.length()];
		for (int i = length - 1; i >= 0; i--) {
			int no = CommonTool.getTools().ArrayUtils.getMaxIndex(status[i]);

			if (status[i][no] > 0) {
				l_path[no] = i + 1;
				r_path[i] = no + 1;
			}
		}

		dataWash();
	}
	//清洗异常数据
	public void dataWash() {
		int max = -1;
		for (int i = 0; i < l_path.length; i++) {
			if (l_path[i] <= max) {
				l_path[i] = 0;
				continue;
			}
			max = l_path[i];
		}
		max = -1;
		for (int i = 0; i < r_path.length; i++) {
			if (r_path[i] <= max) {
				r_path[i] = 0;
				continue;
			}
			max = r_path[i];
		}
	}

	public static void main(String[] args) {
		Item item = new Item("昨天夜里就去哪夜里了晚上不要出去", "夜里就不要出去了");
		item.compare();

		item.builpath();
		item.show();
	}
}
