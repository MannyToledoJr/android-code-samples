这个 project 主要描述怎样通过一个 Activity 启动另一个 Activity，并且获取新的 Activity 执行的结果

初始 MainActivity 中创建一个携带有信息的 intent 实例，然后通过 startActivityForResult(intent, 1); 启动 NewActivity ，当新的 Activity 执行完毕返回时在原来的 MainActivity 中调用 @Override protected void onActivityResult(int requestCode, int resultCode, Intent data); 判断运行状态

app 运行效果：

![](/img/ActivityParameterPassing.gif)


