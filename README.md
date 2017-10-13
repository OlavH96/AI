# AI

## Neural Networks

Check out [this part of the repo](https://github.com/OlavH96/AI/tree/master/src/main/java/neuralnetworks/picture/X_or_O/app).
This is an app that uses neural networks to recognize the inputed drawing to an O or an X. This is neural networks in its simplest form,
but can be expanded to include any type of image, for example the alphabet (my next project).

The network uses 5x5 images as its initial input data, and the perception.nnet contains this initial configuration. The JavaFX app continually
improves the network by adding new datapoints when the user inputs a drawing and presses the 'correct' or 'incorrect' buttons. This improved
network is stored in the app.nnet file, and is loaded at the start of the app, and saved at the end.

All images are scaled to the same 5x5 format, and inputted into the network. You could use larger images to achieve better accuracy, and this
would be needed for text for example. Initially I tried with 3x3, but this was too low fidelity to capture all user input, especially
when the user did not draw in the entire drawing area, which is often the case.

[This is the neural net implementation](https://github.com/OlavH96/AI/blob/master/src/main/java/neuralnetworks/picture/SimpleNeuralNetwork.java) which is a wrapper for the Neuroph neural network framework. It uses the Perceptron style of neural network, using the Sigmoid function, these can be exchanged as required.  
