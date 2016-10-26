# Video Labelizer

The Video Labelizer is a project allowing to Labelize videos easily. You can
use it to label your videos frame by frame.

Currently it allows to open videos, and by clicking on the object you want to
label on the frames.

# Prerequisites

## Operating system

* Windows x64 (tested on Windows 10)
* Linux x64

The needed dll and so are already given in the lib files for these operating
systems. Other operating systems or architectures can be made compatible by
building OpenCV, openH264 and ffmpeg for these platforms.

## Software

* Gradle must be installed and accessible in the `$PATH`
* Java 8

# Usage

To use this project just launch the following command:

```
gradle run
```

It should build and run the labelizer. You can then open any video by clicking
on "File" -> "Open".

# Export format

The file will be named `FILENAME-TIMESTAMP.txt` and contains easily parsable
lines which describe the labeled objects.

```
Frame ([0-9]+)
([0-9]+),([0-9]+)
...
([0-9]+),([0-9]+)
```

The first line contains the position of the frame in the file.
The other lines contain the x, y coordinates of the points which were marked
during the classification.
There can be zero, one or many lines containing coordinates for each frame.
