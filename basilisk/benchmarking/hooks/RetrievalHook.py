class RetrievalHook:
    _name: str = ""

    def __init__(self):
        pass

    @property
    def name(self) -> str:
        return type(self)._name
