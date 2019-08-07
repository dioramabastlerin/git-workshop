package impl

class GitContext<T>(val dir: AbstractWorkingDir<T>) {

    fun commit(fileName: String, message: String?) {
        dir.git("""commit -am "${message ?: commitMessageFor(fileName)} """")
    }

    private fun commitMessageFor(fileName: String) =
            "Commited file $fileName on branch ${dir.currentBranch()} by ${dir.currentUser()}"
}
