node(){
    stage("Git 远程仓库拉取"){
        git url: "https://github.com/ht5603/FrogBookStore",
            credentialsId: "git-global-credentials",
            branch: env.BRANCH_NAME
    }
}
