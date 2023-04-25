import java.util.Scanner;// 导入 Scanner 类
public class QinYeDingCanXiTong { // 定义 秦晔订餐系统  主类
    static String[] Name = {"清补凉", "鸡公煲", "红烧肉", "地三鲜"};//菜品名称
    static double[] prices = {10.0, 27.0, 25.0, 15.0};// 单 价
    static int[] DianZan = new int[4];// 点赞数
    //订单-订餐人餐品信息  餐品信息   送餐时间   送餐地址  总金额  订单状态
    static String[] names = new String[4];     //订餐人姓名
    static String[] dishMsg = new String[4];   //所订菜品名称
    static int[] times = new int[4];           //送餐时间 10 -- 20之间
    static String[] adresses = new String[4];  //送餐地址
    static double[] sumPrices = new double[4]; //总金额
    static int[] states = new int[4];    //订单的状态  0:已预订(未签收) 1:已完成(已签收)

    public static void addOrder() {  // 定义“我要订餐”方法
        System.out.println("******************  我要订餐  *******************");
        System.out.println("------------------  菜品信息  -------------------");
        System.out.println("序号\t名称\t\t价格\t\t点赞");
        for (int j = 0;j < dishMsg.length;j++) {//循环遍历所订菜品名称，以便输出可选菜品信息
            if (dishMsg[j] == null) { //当 dishMsg[i]为空时，表示还有订单可以添加，否则无法添加订单。
                for (int i = 0; i < names.length; i++) { //循环遍历存储订餐人姓名的数组
                    // 下面依次显示输出 菜品 单价 点赞数
                    System.out.println((i + 1) + "\t" + Name[i] + "\t" + prices[i] + "\t" + DianZan[i]);
                }
                System.out.print("请选择菜单序号: ");
                Scanner sc = new Scanner(System.in);
                int xuanze = sc.nextInt();
                // 判断输入的菜品序号是否合法，如果不合法，则会提示并重新输入
                while(xuanze < 1 || xuanze > 4) {
                    System.out.println("输入错误,没有这个菜品！\n请重新输入: ");
                    xuanze = sc.nextInt();
                }
                System.out.print("请输入订餐人姓名: ");
                String XingMing = sc.next();

                System.out.print("请输入订餐数量: ");
                int numbers = sc.nextInt();

                System.out.print("请输入送餐时间: ");
                int time1 = sc.nextInt();
                while (time1 < 10 || time1 > 20) { //判断输入的送餐时间是否合法，如果不合法则提示并重新输入
                    System.out.println("输入有误！请重新输入: ");
                    time1 = sc.nextInt();
                }

                System.out.print("请输入送餐地址: ");
                String dizhi = sc.next();

                System.out.println("订餐成功！！！！");

                // 静态数组Name[i]为存储菜品名称，下标为0 1 2 3 而菜品序号为1 2 3 4 ，所以序号要减 1
                String messages = Name[xuanze - 1] + numbers + "份";//所订餐品名称和份数
                System.out.println("你订的是: " + messages);
                System.out.println("送餐时间是: " + time1 + "点");
                System.out.println("送餐地址是: " + dizhi);
                double yunfei = (numbers * prices[xuanze - 1] > 50) ? 0 : 5;//三目运算符，如果订单金额大于50元则免运费，否则运费为5元
                System.out.println("餐费是: " + numbers * prices[xuanze - 1] + ";" +
                                   "运费: " + yunfei + ";" + "总计: " +
                                   (numbers * prices[xuanze - 1] + yunfei) + "元");
                //订餐完后保存订单信息
                names[j] = XingMing;
                dishMsg[j] = messages;
                times[j] = time1;
                adresses[j] = dizhi;
                sumPrices[j] = numbers * prices[xuanze - 1] + yunfei;
                break;//跳出 for 循环
            }
            else if (j == dishMsg.length-1){ // j = 3 表示已经订了 4 份，订单已满
                System.out.println("订单已满！无法继续订单！");
            }
        }
    }

    public static void viewOrders() {//  定义“查看餐袋”方法
        System.out.println("******************查看餐袋*****************");
        for (int i = 0; i < names.length; i++) { //static names[] 数组来记录订单信息中的订餐人姓名
                                                 // 做多保存4个人姓名
            if (names[i] != null) {  // 如果names[i]存在订餐人姓名就会执行下面代码
                //输出格式和对应的内容
                System.out.println("序号");
                System.out.println(i + 1);//因为i是从0开始，而数组names[]下表从1开始，所以要加 1
                System.out.println("订餐人姓名：" + names[i]);
                System.out.println("菜名和份数：" + dishMsg[i]);
                System.out.println("送餐时间：" + times[i] + "点");
                System.out.println("送餐地址：" + adresses[i]);
                System.out.println("总金额：" + sumPrices[i] + "元");
                if (states[i] == 0) {// states[i] ; 0 表示 已预订但还没签收； 1 表示 已完成，即已签收
                    System.out.println("订单状态：已预订");
                } else {// states[i] = 1 即：已完成！
                    System.out.println("订单状态：已完成.");
                }
            }
        }
        //使用 names[i] 静态数组来记录订单信息中的订餐人姓名。
        // 如果 names[i]数组中第一个元素为空，则说明当前还没有任何订单信息被记录。
        // 在这种情况下，输出("还没有人下单！赶紧去下单吧！！！！")
        if (names[0] == null) {
            System.out.println("还没有人下单！赶紧去下单吧！！！！");
        }
    }

    public static void receiveOrder() {//  定义  签收订单  方法
        System.out.println("******************签收订单*****************");
        //先查找单号：
        boolean danhao = false;//标记是否找到了单号,false: 没找到，true : 已找到
        System.out.println("请选择要签收的订单序号: (序号：1 -- 4)");
        Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt();
        while (s < 1 || s > 4){//判断输入是否合法，不合法则提示并重新输入
            System.out.println("输入有误！请重新输入: ");
            s = scanner.nextInt();
        }
        for (int i = 0; i < names.length; i++) {// 遍历names[]数组查找订单
            if (names[i] != null && states[i] == 1 && i == s - 1) {// 存在订单信息并已签收
                System.out.println("你的订单已经完成！！！无法重复确认。。。");
                danhao = true;
            } else if (names[i] != null && states[i] == 0 && i == s - 1) {// 存在订单信息并现在签收
                danhao = true;
                states[i] = 1;//已经完成签收
                System.out.println("订单编号"+s+"已经签收完毕！！！欢迎下次光临！");
            }
        }
        if (danhao == false) { //如果未找到单号会提示信息
            System.out.println("没有找到单号！！！");
        }
    }

    public static void deleteOrder(){ // 定义 删除订单 方法
        System.out.println("******************删除订单*****************");
        System.out.println("请输入要删除的订单号: ");
        Scanner scanner = new Scanner(System.in);// 创建Scanner对象，用于从控制台读取输入要删除的订单号
        int delete = scanner.nextInt();//从控制台输入 需要删除的订单号
        while (delete < 1 || delete > 4){ //  检查 输入 的单号是否为 1 -- 4 ，不是则一直提示重新输入
            System.out.println("输入有误！请重新输入: ");
            delete = scanner.nextInt();
        }
        boolean deleted = false; // 定义标志位，是否已经删除
        boolean found = false; // 定义标志位，是否找到订单
        for (int i = 0; i < names.length; i++){ //names[] 表示存储订餐人姓名
            if (names[i] != null && states[i] == 1 && i == delete - 1){ // 找到要删除的订单，且已签收
                deleted = true;  //已 删 除
                found = true; // 已找到订单
                if (i == names.length - 1){// i等于3，为最后一位。如果是最后一位则置空
                    names[i] = null;
                    adresses[i] = null;
                    times[i] = 0;
                    states[i] = 0;
                }
                else { // 不是最后一位则 把 数据往前移，覆盖要删除的订单
                    for (int j = i; j < names.length - 1; j++){ // 把后一位的数据往前移
                        names[j] = names[j+1];
                        adresses[j] = adresses[j+1];
                        times[j] = times[j+1];
                        states[j] = states[j+1];
                    }
                }
                System.out.println("你的订单已经删除成功！\n欢迎下次光临秦晔！！！");
                break;
            } else if (names[i] != null && states[i] == 0 && i == delete - 1){ // 找到要删除的订单，但未签收
                found = true;
                System.out.println("你的订单还没有签收！无法删除！！！请先签收！！！");//输出提示信息
                break;
            }
        }
        if (!found){ // 未找到订单
            System.out.println("你的订单不存在!!!!!!");
        }
    }

    public static void dianZan(){//定义  我要点赞  静态方法
        System.out.println("******************我要点赞*****************");
        System.out.println("******************菜品信息*****************");
        System.out.println("序号\t\t名称\t\t价格\t\t点赞");
        //遍历数组，输出每道菜品的信息，包括名称、价格和点赞数
        int len = dishMsg.length;
        for (int i=0;i < len;i++){
            System.out.println((i+1) +"\t\t" + Name[i] +"\t" + prices[i] + "\t"+ DianZan[i]);
        }
        System.out.println("*****************************************");

        //提示用户输入要点赞的菜品序号
        System.out.println("请选择要点赞的菜单序号: (序号：1--4 || 输入0返回功能菜单 )");
        Scanner sc = new Scanner(System.in);
        int caidanxuhao = sc.nextInt();
        if(caidanxuhao == 0){ // 输入 0 返回
            return;
        }
        //检查用户输入是否为 1-4 之间的数字，否则提示错误信息并重新输入
        while (caidanxuhao < 1 || caidanxuhao > 4){
            System.out.println("输入有误！！！请重新输入: ");
            caidanxuhao = sc.nextInt();
        }
        /*DianZan[i]数组，它存储了菜品被点赞的次数，caidanxuhao是一个整数变量，
        表示用户选择点赞的菜品的序号。因为数组的下标从0开始，而用户选择的序号从1开始，所以需要减1来获取正确的数组下标
        DianZan数组中下标为caidanxuhao-1的元素自增 1，即将选择的菜品的点赞次数加 1。
        */
        DianZan[caidanxuhao - 1]++;
        System.out.println("你为编号: " + caidanxuhao + "菜品点赞成功！！！");
        // 点赞成功后再次显示菜品的信息，包括名称、价格和点赞数，可直观点赞是否成功！
        for (int i = 0;i < dishMsg.length;i++){
                System.out.println((i + 1) + "\t\t" + Name[i] + "\t" + prices[i] + "\t" + DianZan[i]);
        }
    }

    public static void tuiChu(){  // 退出系统
        System.out.println(
                "*************已经退出系统，谢谢你的本次使用！************\n" +
                "**************欢迎下次使用秦晔订餐！！！*************");
        System.exit(0);//表示退出程序 0:表示正常结束程序  1:表示异常关闭
    }
    public static void menu() {  // 项目的菜单
        System.out.println(
                "===========欢迎使用秦晔订餐系统!==========" +
                "\n*****************************\n" +
                "   1.我要订餐\n" +
                "   2.查看餐袋\n" +
                "   3.签收订单\n" +
                "   4.删除订单\n" +
                "   5.我要点赞\n" +
                "   6.退出系统\n" +
                "*******************************");
        System.out.print("请选择操作(只能输入数字): ");// print表示不换行，要输入的数字和输出内容在同一行
        Scanner sc = new Scanner(System.in);//Scanner类，这是一个用于获取控制台输入文本的程序
        int choice = sc.nextInt();// 获取 整形数字
        switch (choice) {
            // 根据 输入的数字去判断执行哪个方法
            case 1:
                addOrder();      //我要订餐
                break;// 用 break 来跳出离它最近的循环
            case 2:
                viewOrders();    // 查看餐袋
                break;
            case 3:
                receiveOrder();  //签收订单
                break;
            case 4:
                deleteOrder();   //删除订单
                break;
            case 5:
                dianZan();       //我要点赞
                break;
            case 6:
                tuiChu();       // 退出系统
            default:
                // 当控制台输入的数字 不是 1--6 的时候执行该语句
                //提示错误，重新输入 :  并一直检查输入的数字的对错，直到输入正确
                System.out.println("输入错误，请重新输入！");
                break;
        }
    }
    public static void main(String[] args) {
        // 主方法 程序运行的开端
        do {
           menu();// 不断循环执行 menu()菜单
        }
        while (true);
    }
}