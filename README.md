# rsocket-fireandforget-example
![Build](https://github.com/gregwhitaker/rsocket-fireandforget-example/workflows/Build/badge.svg)

An example of sending messages between applications with the fire-and-forget interaction model in [RSocket](http://rsocket.io).

In this example the `count-client` streams integers from `1` to `1000` to the `count-service` using the fire-and-forget interaction model.

## Building the Example
Run the following command to build the example:

    ./gradlew clean build
    
## Running the Example
Follow the steps below to run the example:

1. Run the following command to start the `count-service`:

        ./gradlew :count-service:run
        
2. In a new terminal, run the following command to start sending integers with the `count-client`:

        ./gradlew :count-client:run
        
    If successful, you will see that 1000 integers were sent in the client terminal:
    
        [main] INFO example.count.client.CountClient - Sending: 997
        [main] INFO example.count.client.CountClient - Sending: 998
        [main] INFO example.count.client.CountClient - Sending: 999
        [main] INFO example.count.client.CountClient - Sending: 1000
        
    and you will see that 1000 integers were received in the service terminal:
    
        [reactor-tcp-nio-3] INFO example.count.service.CountService - Received: 997
        [reactor-tcp-nio-3] INFO example.count.service.CountService - Received: 998
        [reactor-tcp-nio-3] INFO example.count.service.CountService - Received: 999
        [reactor-tcp-nio-3] INFO example.count.service.CountService - Received: 1000
        
    **NOTE:** You may not receive all 1000 messages on the service side. The fire-and-forget interaction model is not acked 
    and is intended for situations where losing a few messages is acceptable.

## Bugs and Feedback
For bugs, questions, and discussions please use the [Github Issues](https://github.com/gregwhitaker/rsocket-fireandforget-example/issues).

## License
MIT License

Copyright (c) 2020 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
