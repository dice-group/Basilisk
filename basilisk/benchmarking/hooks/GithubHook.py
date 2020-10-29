from basilisk.benchmarking.hooks.RetrievalHook import RetrievalHook


class GithubHook(RetrievalHook):
    _name: str = "Github Hook"

    def __init__(self):
        super().__init__()
