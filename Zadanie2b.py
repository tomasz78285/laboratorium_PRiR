import tensorflow as tf
import numpy as np
from tensorflow import keras

def my_function(x,y):
    xy = sin(sqrt(x*x + y*y))/sin(x*x + y*y)
    return xy


model = tf.keras.Sequential([keras.layers.Dense(units=1, input_shape=[1])])

model.compile(optimizer='sgd', loss='mean_squared_error')

xs = np.array([-1.0, 0.0, 1.0, 2.0, 3.0, 4.0], dtype=float)
ys = np.array([-2.0, 1.0, 4.0, 7.0, 10.0, 13.0], dtype=float)

model.fit(xs, ys, epochs=500)

print(model.predict([10.0]))

import tensorflow as tf
device_name = tf.test.gpu_device_name()
if device_name != '/device:GPU:0':
	raise SystemError('GPU device not found')
print('Found GPU at: {}'.format(device_name))
