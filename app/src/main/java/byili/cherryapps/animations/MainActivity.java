package byili.cherryapps.animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

public class MainActivity extends AppCompatActivity implements com.nineoldandroids.animation.Animator.AnimatorListener {

    TextView tv_animation_a, textView2, textView3;
    ImageView img_a, img_b, img_c, img_d, img_e, img_f, img_g;
    Button btnExample, btnExample2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_animation_a          =   (TextView) findViewById(R.id.txt_animation_a);
        textView2               =   (TextView) findViewById(R.id.textView2);
        textView3               =   (TextView) findViewById(R.id.textView3);
        img_a                   =   (ImageView) findViewById(R.id.img_avatar1);
        img_b                   =   (ImageView) findViewById(R.id.img_avatar2);
        img_c                   =   (ImageView) findViewById(R.id.img_avatar3);
        img_d                   =   (ImageView) findViewById(R.id.img_avatar4);
        img_e                   =   (ImageView) findViewById(R.id.img_avatar5);
        img_f                   =   (ImageView) findViewById(R.id.img_avatar6);
        img_g                   =   (ImageView) findViewById(R.id.img_avatar7);

        btnExample = (Button) findViewById(R.id.btn_example);
        btnExample2 = (Button) findViewById(R.id.btn_example2);



    }

    public void propertyText(View view){
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(tv_animation_a, "alpha", 0.2f);
        fadeAnim.start();
    }
    public void propertyImage(View view){
        ObjectAnimator fadeAltAnim = ObjectAnimator.ofFloat(img_a, String.valueOf(View.ALPHA), 0, 1);
        fadeAltAnim.start();
    }

    public void  propertyAnimationScaleX(View view){
        ObjectAnimator scaleAnim = ObjectAnimator.ofFloat(img_b, "scaleX", 1.0f, 2.0f);
        scaleAnim.setDuration(3000);
        scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnim.setRepeatMode(ValueAnimator.REVERSE);
        scaleAnim.start();
    }

    public void  propertyAnimationScaleY(View view){
        ObjectAnimator moveAnim = ObjectAnimator.ofFloat(img_c, "Y", 420);
        moveAnim.setDuration(2000);
        moveAnim.setInterpolator(new BounceInterpolator());
        moveAnim.start();
    }

    public void animgListener(View view){
        ObjectAnimator anim = ObjectAnimator.ofFloat(img_d, "alpha", 0.2f);
        anim.addListener(this);
        anim.start();
    }

    public void backgroundScale(View view){
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(textView2, "scaleX", 1.0f, 2.0f).setDuration(2000),
                ObjectAnimator.ofFloat(textView2, "scaleY", 1.0f, 2.0f).setDuration(2000),
                ObjectAnimator.ofObject(textView2, "backgroundColor", new ArgbEvaluator(),
          /*Red*/0xFFFF8080, /*Blue*/0xFF8080FF).setDuration(2000)
        );
        set.start();
    }
    public void scaleTraslate(View view){
        // Define first set of animations
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(textView3, "scaleX", 2.0f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(textView3, "scaleY", 2.0f);
        AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(anim1, anim2);
        // Define second set of animations
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(img_e, "X", 80);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(img_e, "Y", 50);
        AnimatorSet set2 = new AnimatorSet();
        set2.playTogether(anim3, anim4);
        // Play the animation sets one after another
        AnimatorSet set3 = new AnimatorSet();
        set3.playSequentially(set1, set2);
        set3.start();
    }

    public void are(View view) {
        // Create two animations to play together
        /*ObjectAnimator bounceAnim = ...;
        ObjectAnimator squashAnim = ...;*/

        ObjectAnimator bounceAnim = ObjectAnimator.ofFloat(textView3, "alpha", 0.2f);

        ObjectAnimator squashAnim = ObjectAnimator.ofFloat(img_f, "X", 750);
        ObjectAnimator squashAnim2 = ObjectAnimator.ofFloat(img_f, "Y", 40);

        // Construct set 1 playing together
        AnimatorSet bouncer = new AnimatorSet();
        bouncer.play(bounceAnim).with(squashAnim).with(squashAnim2);
        // bouncer.play(bounceAnim).with(squashAnim2);
        // Create second animation to play after
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(img_g, "alpha", 1f, 0f);
        fadeAnim.setDuration(250);
        // Play bouncer before fade
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(bouncer).before(fadeAnim);
        animatorSet.start();
    }

    public void addButton(View view){
        btnExample.animate().alpha(0.2f).xBy(-100).yBy(100);
        btnExample.animate().alpha(0.5f).rotation(90f).
                scaleX(2).xBy(100).yBy(100).setDuration(1000).setStartDelay(10).
                setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Toast.makeText(MainActivity.this, "Started...", Toast.LENGTH_SHORT).show();
                    };
                });
    }


    public void addButtonSupport4(View view){
        ViewCompat.animate(btnExample2).alpha(0.2f).xBy(-100).yBy(100);
    }

    @Override
    public void onAnimationStart(com.nineoldandroids.animation.Animator animation) {
    }

    @Override
    public void onAnimationEnd(com.nineoldandroids.animation.Animator animation) {
        Toast.makeText(MainActivity.this, "End!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnimationCancel(com.nineoldandroids.animation.Animator animation) {

    }

    @Override
    public void onAnimationRepeat(com.nineoldandroids.animation.Animator animation) {

    }
}
