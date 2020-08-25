# [Git 分支管理常见三种方式](https://blog.csdn.net/u011485314/article/details/89915824)

> Git 分支管理常见三种方式
  TBD（Trunk-based development、单主干）
  GitHub flow
  git-flow
1. TBD
所有团队成员都在单个主干分支上进行开发。
发布时，先考虑使用标签 Tag， 如果打标签不能满足要求，则从主干创建发布分支。
Bug在主干上修复，然后挑选时机发布到 发布分支上
优点: 分支少，开发人员不需要频繁在不同的分支之间切换。
缺点: 因为主干分支是所有开发人员公用的，一个开发人员引入的 bug 可能对其他很多人造成影响。
2. GitHub flow
GitHub使用的一种简单流程，使用两类分支，Master、代码修改分支，主要对应GitHub的pull、request功能。

master分支
包含稳定的代码，该分支已经或即将被部署到生产环境，分支的作用是提供一个稳定可靠的代码基础。任何开发人员都不允许把未测试或未审查的代码直接提交到 master 分支。

代码修改分支
当需要进行任何修改时（包括 bug 修复、hotfix、新功能开发等），总是从 master 分支创建新分支。

分支合并流程
当新分支中的代码全部完成之后，通过 GitHub 提交一个新的 pull request。团队中的其他人员会对代码进行审查，提出相关的修改意见。由持续集成服务器对新分支进行自动化测试。当代码通过自动化测试和代码审查之后，该分支的代码被合并到 master 分支。再从 master 分支部署到生产环境

3. Git-flow
目前流传最广的 Git 分支管理实践，围绕的核心概念是版本发布（release）。git-flow 流程中包含 5 类分支，分别是 master、develop、feature、release 和 hotfix。

Master 分支中包含的是可以部署到生产环境中的代码，这一点和 GitHub flow 是相同的

develop 分支中包含的是下个版本需要发布的内容。当 develop 分支集成了足够的新功能和 bug 修复代码之后，通过一个发布流程来完成新版本的发布。发布完成之后，develop 分支的代码会被合并到 master 分支中。

feature、release和 hotfix为均为临时分支，只在需要时才从 develop 或 master 分支创建，在完成之后合并到 develop 或 master 分支。合并完成之后该分支被删除。

分支类型	命名规范	创建自	合并到	说明
feature	feature/*	develop	develop	新功能
release	release/*	develop	develop和master	一次新版本的发布
hotfix	hotfix/*	master	develop和master	生产环境中发现的紧急 bug 的修复
因为 git-flow 相关的流程比较繁琐和难以记忆，在实践中一般使用辅助脚本来完成分支创建、切换和合并等工作。

总结
如果发布周期较长，则 git-flow 是最好的选择。git-flow 可以很好地解决新功能开发、版本发布、生产系统维护等问题；
如果发布周期较短，则 TBD 和 GitHub flow 都是不错的选择
