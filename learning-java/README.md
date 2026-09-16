# learning-java —— 第一阶段：Java 基础补强

对应任务书第一阶段（2~3 周），目标：独立完成学生管理控制台程序。

## 目录结构

| 目录 | 主题 | 知识点 | 运行方式 |
|------|------|--------|----------|
| `day01-oop/` | 面向对象复习 | 封装、构造方法、getter 校验、toString、equals/hashCode | `javac -encoding UTF-8 Student.java Main.java && java Main` |
| `day02-collections/` | 集合 + 控制台程序 | ArrayList、HashMap、Comparator 排序、自定义异常 | `javac -encoding UTF-8 Student.java StudentManager.java && java StudentManager` |
| `day03-advanced/` | 异常/泛型/Stream | try-catch-finally、泛型类/方法、Lambda、Stream、Optional | 见各文件头部注释 |

## 第一周任务清单

- [x] Day 1：复习 OOP，编写 Student 类（封装 + 参数校验）
- [x] Day 2：ArrayList/HashMap/HashSet，完成学生管理控制台程序
- [x] Day 3：异常、泛型、Lambda、Stream
- [ ] Day 4：MySQL 建库建表（student 等表），基础 CRUD
- [ ] Day 5：JOIN、GROUP BY、子查询
- [ ] 周末：整理 README、上传 GitHub、检查旧医务室系统

## 注意事项

- 每天（每个目录）代码自包含，可在目录内独立编译运行。
- Windows 终端中文乱码时：先执行 `chcp 65001` 切换 UTF-8。
- 编译产生的 `.class` 文件不要提交，`.gitignore` 建议加入 `*.class`。