
## Github Triangle Workflow

#### First Time Setup

1. Fork the main github repository.

2. Open a terminal in some directory to store git repositories:

> git clone https://github.com/*YourUserName*/SoftwareDev.git

This will create a *local* copy of the repository, separate from the *remote* one on Github.

3. Add a reference to the *remote* master repository:

> git remote add upstream https://github.com/Sal-Hussain/SoftwareDev.git

The master branch here on "upstream" will be the source of the *pure* working code. New development changes
should start from master. NEVER push to master, prefer to create a pull request from your fork.

4. Check the remotes using:

> git remote -v

Note your original fork repository is named "origin".
Your local repository, along with the two remotes form the three points in the "triangle".

#### When Starting Development on a New Feature

1. Retrieve all information about changes from the remotes:

> git fetch --all

2. Make sure you are going to be on master (the clean starting code):

> git checkout master

3. Make your local master branch an exact copy of the main master branch:

> git reset upstream/master --hard

4. Make a new branch to develop your feature: 

> git checkout -b master *branchname*

The *branchname* should appropriately describe the feature, yet be concise.

#### When Starting Development on an Existing Feature

After creating the new feature branch, or when starting each programming session, or if other group members
have merged changes to master, follow these steps:

1. Retrieve all information about changes from the remotes:

> git fetch --all

2. Ensure you are working on top of the latest master code:

> git rebase upstream/master

This will put all your commits after the commits of master. 
Include the `-i` flag if you wish to do an *interactive rebase*, which allows cleaning up
your commit history.

3. Make your changes to the code.

4. *Stage* (tell git you want to commit these changes) your files (or specific lines). I recommend 
some git integrated software (such as Visual Studio Code) to handle this, over command line git.

5. Commit your changes with an appropriate commit message. Commits should be atomic, meaning they
should be a single, unbreakable unit of change that does not have any errors.

6. Optionally, you may amend the previous commit to: add newly *staged* files to your latest commit, or
update the commit message.

> git commit --amend

7. Repeat until feature development is complete is done.

8. Push (send commits) to your fork repository.

> git push origin *branchname*

If you have rebased to change history, or otherwise have a different commit history on your local vs remote branches, 
use the `--force` flag to overwrite the remote branch.

9. Create a pull request from your fork and *branchname* to the main upstream master branch. Have others review
your change and merge. After merge it will be available for other group members when they `fetch`.

#### If Development of an Unmerged Branch is Required

Sometimes you may want to continue from an unmerged branch instead of master.

1. Add a reference to the other group member's *remote* fork repository:

> git remote add *groupmembername* https://github.com/*theirusername*/SoftwareDev.git

2. Retrieve all information about changes from the remotes:

> git fetch --all

3. Continue development as normal except rebasing on top of their work:

> git rebase *groupmembername*/*branch*

