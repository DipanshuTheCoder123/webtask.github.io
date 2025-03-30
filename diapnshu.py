import cv2
import torch
from ultralytics import YOLO
from IPython.display import display, clear_output
import PIL.Image
import time


import cv2
import numpy as np

# Create a dummy image (black image)
dummy_img = np.zeros((640, 640, 3), dtype=np.uint8)

# Perform object detection on dummy image
results = model(dummy_img)

# Print results to check model output
print("✅ Model is working!")



cap.set(cv2.CAP_PROP_FRAME_WIDTH, 1280)  # Set width to 1280 pixels
cap.set(cv2.CAP_PROP_FRAME_HEIGHT, 720)  # Set height to 720 pixels





# Define waste-related classes (Update this based on your model)
waste_classes = ["waste", "plastic", "bottle", "paper", "can", "garbage"]

# Object detection and filtering
for result in results:
    for box in result.boxes:
        x1, y1, x2, y2 = map(int, box.xyxy[0])
        conf = float(box.conf[0])
        label = result.names[int(box.cls[0])]  # Get class name

        # ✅ Show only waste-related objects
        if label in waste_classes:
            cv2.rectangle(frame, (x1, y1), (x2, y2), (0, 255, 0), 2)
            cv2.putText(frame, f"{label}: {conf:.2f}", (x1, y1 - 10),
                        cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 255, 0), 2)






import cv2
import torch
from ultralytics import YOLO
from IPython.display import display, clear_output
import PIL.Image

# Load your trained waste detection model
model = YOLO("waste-detection.pt")

# Print class names in the model (To verify waste class index)
print(model.names)  

# ✅ Define waste-related classes (Update these based on your model)
waste_classes = ["waste", "plastic", "bottle", "paper", "can", "garbage"]

# Open the camera
cap = cv2.VideoCapture(0)
cap.set(cv2.CAP_PROP_FRAME_WIDTH, 1280)  
cap.set(cv2.CAP_PROP_FRAME_HEIGHT, 720)  

if not cap.isOpened():
    print("❌ Error: Could not open camera.")
    exit()

try:
    while True:
        ret, frame = cap.read()
        if not ret:
            print("❌ Failed to grab frame")
            break

        # Perform object detection
        results = model(frame)

        # Draw bounding boxes only for waste-related objects
        for result in results:
            for box in result.boxes:
                x1, y1, x2, y2 = map(int, box.xyxy[0])
                conf = float(box.conf[0])
                label = result.names[int(box.cls[0])]

                # ✅ Show only waste-related objects
                if label in waste_classes:
                    cv2.rectangle(frame, (x1, y1), (x2, y2), (0, 255, 0), 2)
                    cv2.putText(frame, f"{label}: {conf:.2f}", (x1, y1 - 10),
                                cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 255, 0), 2)

        # Convert frame to an image for Jupyter display
        img = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
        img = PIL.Image.fromarray(img)

        # Display the image in Jupyter Notebook
        clear_output(wait=True)
        display(img)

except KeyboardInterrupt:
    print("🛑 Stopped by user")

# Release resources
cap.release()
cv2.destroyAllWindows()
