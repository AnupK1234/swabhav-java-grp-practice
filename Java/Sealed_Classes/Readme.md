| Keyword      | Description                               |
| ------------ | ----------------------------------------- |
| `sealed`     | Restricts who can extend or implement     |
| `permits`    | Lists allowed subclasses                  |
| `final`      | No one can subclass further               |
| `non-sealed` | Removes restriction, making it open again |

Any class that extends a sealed class must declare itself as one of the following:

final – no one else can extend it

sealed – further restrict who can extend it

non-sealed – anyone can extend it

