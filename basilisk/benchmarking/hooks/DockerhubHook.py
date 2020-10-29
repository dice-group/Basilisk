from basilisk.benchmarking.hooks.RetrievalHook import RetrievalHook


class DockerhubHook(RetrievalHook):
    _name: str = "Dockerhub Hook"

    def __init__(self):
        super().__init__()
